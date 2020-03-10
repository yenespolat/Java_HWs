/* YASIN ENES POLAT 150117015
 * In this homework i did an person hierarchy implementation for company-like thing.
 * I used throw expressions, text io and polymorphism during implementation.
 * Program simply reads strings from input.txt file and creates object with those strings. Then prints the informations about objects out to output.txt file
*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Test {

	public static void main(String[] args) throws Exception {
		
		File input = new File("input.txt");
		
			Scanner read = new Scanner(input);
		
			ArrayList<Object> objs = new ArrayList<Object>();
					
		while (read.hasNext()) {
			String line = read.nextLine();
			String[] str = line.split(" ");
			createObj(objs, str);
		}
		distributeToManagers(objs);
		operations(objs);
		printOut(objs);
		
		read.close();
		
	}
	
	public static void operations (ArrayList<Object> al) throws Exception { //This method do operations given in pdf.

		int sumSE = 0;
		SalesEmployee sE = new SalesEmployee();
		
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) instanceof Manager) {
				Manager m = new Manager();
				m = (Manager)al.get(i);
				m.distributeBonusBudget();
				m.raiseSalary(0.2);
			}
			else if (al.get(i) instanceof RegularEmployee && !(al.get(i) instanceof SalesEmployee) && !(al.get(i) instanceof Developer)) {
				RegularEmployee r = new RegularEmployee();
				r = (RegularEmployee)al.get(i);
				r.raiseSalary(0.3);
			}
			else if (al.get(i) instanceof Developer) {
				Developer d = new Developer();
				d = (Developer)al.get(i);
				d.raiseSalary(0.23);				
			}
			else if (al.get(i) instanceof SalesEmployee) {
				SalesEmployee s = new SalesEmployee();
				s = (SalesEmployee)al.get(i);
				s.raiseSalary(0.18);
				int sum = 0;
				for(int j = 0; j < s.getSales().size(); j++) { //This loop finds sum of sales for each SalesEmployee.
					sum += s.getSales().get(j).getPrice();
					System.out.println(sum);
				}
				if (sum > sumSE) {
					sE = s;
					sumSE = sum;
				}
					
					
			}
		}
		
		sE.raiseSalary(1000);
		
	}
		
	public static Calendar createDate (String str) { //This method simply creates the calendar object with the given string.
		
		String[] s = str.split("/");
		Calendar c = new GregorianCalendar(Integer.parseInt(s[2]), Integer.parseInt(s[1])-1, Integer.parseInt(s[0]));
		return c;
		
		
	}
	
	public static void distributeToManagers (ArrayList<Object> al) { //This method adds regular employeess to its department's manager's arraylist.
		for (int i = 0; i < al.size(); i++) {
			RegularEmployee temp = new RegularEmployee();
			if (al.get(i) instanceof RegularEmployee) {
				temp = (RegularEmployee)al.get(i);
				for (int j = 0; j < al.size(); j++) {
					Manager tempp = new Manager();
					if (al.get(j) instanceof Manager) {
						tempp = (Manager)al.get(j);
						if (tempp.getDepartment().getDepartmentName().equalsIgnoreCase(temp.getDepartment().getDepartmentName())) {
							tempp.addEmployee(temp);
						}
					}
				}
			}
		}
	}
	
	public static void createObj (ArrayList<Object> al, String[] str) throws Exception { //This method creates the objects with the given strings
		Department d = new Department();
		Person p = new Person ();
		Employee emp = new Employee();
		RegularEmployee rEmp = new RegularEmployee ();
		if (str[0].equalsIgnoreCase("Department")) {
			Department dep = new Department(Integer.parseInt(str[1]), str[2]);
			al.add(dep);
		}
		else if (str[0].equalsIgnoreCase("RegularEmployee")) {
			if (str.length <= 5) {
				RegularEmployee e = new RegularEmployee(emp.getEmployee(str[1], al), Double.parseDouble(str[2]));
				al.remove(emp.getEmployee(str[1], al));
				al.add(e);
			}
			else {
				RegularEmployee e = new RegularEmployee (Integer.parseInt(str[3]), str[1], str[2], str[4], createDate(str[5]), str[6], str[7], Double.parseDouble(str[8]), createDate(str[9]), d.findDepartment(str[10], al), Double.parseDouble(str[11]));
				al.add(e);
			}
			
		}
		else if (str[0].equalsIgnoreCase("Customer")) {
			
			ArrayList<Product> prdAl = new ArrayList<Product>();
			ArrayList<Object> temp = new ArrayList<>(al);
			int selector = 0;
			for (int i = str.length - 1; i >= 2; i--) { //These loops finds the projects or products and add them to arraylist into the object.
				for (int j = 0; j < temp.size(); j++) {
					Product tmp = new Product();
					if (temp.get(j) instanceof Product) {
						tmp = (Product)temp.get(j);
						if (str[i].equalsIgnoreCase(tmp.getProductName())) {
							prdAl.add(tmp);
							temp.remove(j);
							selector = i;
							j--;
						}
					}
				}
			}
			if (selector <= 2) { //If the given string includes just id, then this part finds the person and assigns it to new customer object.
				Customer e = new Customer(p.getPerson(str[1], al), prdAl);
				al.add(e); 
			}
			else {
				Customer e = new Customer(Integer.parseInt(str[3]), str[1], str[2], str[4], createDate(str[5]), str[6], str[7], prdAl);
				al.add(e);
				
			}
		}
		else if (str[0].equalsIgnoreCase("Developer")) {
			ArrayList<Project> prjAl = new ArrayList<Project>();
			ArrayList<Object> temp = new ArrayList<Object>(al);
			int selector = 0;
			for (int i = str.length - 1; i >= 2 ; i--) {
				for (int j = 0; j < temp.size(); j++) {
					Project tmp = new Project();
					if (temp.get(j) instanceof Project) {
						tmp = (Project)temp.get(j);
						if (str[i].equalsIgnoreCase(tmp.getProjectName())) {
							prjAl.add(tmp);
							temp.remove(j);
							selector = i;
							j--;
						}
					}
				}
			}
			if (selector <= 2) {
				Developer e = new Developer(rEmp.getRegularEmployee(str[1], al), prjAl);
				al.remove(rEmp.getEmployee(str[1], al));
				al.add(e);
			}
			else {
				Developer e = new Developer(Integer.parseInt(str[3]), str[1], str[2], str[4], createDate(str[5]), str[6], str[7], Double.parseDouble(str[8]), createDate(str[9]), d.findDepartment(str[10], al), Double.parseDouble(str[11]), prjAl);
				al.add(e);
			}
		}
		else if (str[0].equalsIgnoreCase("Employee")) {
			if (str.length <= 5) {
				Employee e = new Employee(p.getPerson(str[1], al), Double.parseDouble(str[2]), createDate(str[3]), d.findDepartment(str[4], al));
				al.remove(p.getPerson(str[1], al));
				al.add(e);
			}
			else {
				Employee e = new Employee (Integer.parseInt(str[3]), str[1], str[2], str[4], createDate(str[5]), str[6], str[7], Double.parseDouble(str[8]), createDate(str[9]), d.findDepartment(str[10], al));
				al.add(e);
			}
		}
		else if (str[0].equalsIgnoreCase("Manager")) {
			if (str.length <= 5) {
				Manager mng = new Manager (emp.getEmployee(str[1], al), Double.parseDouble(str[2]));
				al.remove(emp.getEmployee(str[1], al));
				al.add(mng);
			}
			else {
				Manager mng = new Manager (Integer.parseInt(str[3]), str[1], str[2], str[4], createDate(str[5]), str[6], str[7], Double.parseDouble(str[8]), createDate(str[9]), d.findDepartment(str[10], al), Double.parseDouble(str[11]));
				al.add(mng);
			}
		}
		else if (str[0].equalsIgnoreCase("Person")) {
			Person psn = new Person (Integer.parseInt(str[3]), str[1], str[2], str[4], createDate(str[5]), str[6], str[7]);
			al.add(psn);

		}
		else if (str[0].equalsIgnoreCase("Product")) {
			Product prdct = new Product(str[1], createDate(str[2]), Integer.parseInt(str[3]));
			al.add(prdct);
		}
		else if (str[0].equalsIgnoreCase("Project")) {
			Project prj = new Project(str[1], createDate(str[2]), str[3]);
			al.add(prj);
		}
		else if (str[0].equalsIgnoreCase("SalesEmployee")) {
			ArrayList<Product> prdAl = new ArrayList<Product>();
			ArrayList<Object> temp = new ArrayList<Object>(al);
			int selector = 0;
			for (int i = str.length - 1; i >= 2; i--) {
				for (int j = 0; j < temp.size(); j++) {
					Product tmp = new Product();
					if (temp.get(j) instanceof Product) {
						tmp = (Product)temp.get(j);
						if (str[i].equalsIgnoreCase(tmp.getProductName())) {
							prdAl.add(tmp);
							temp.remove(j);
							selector = i;
							j--;
						}
					}
				}
			}
			
			if (selector <= 2) {
				SalesEmployee e = new SalesEmployee(rEmp.getRegularEmployee(str[1], al), prdAl);
				al.remove(rEmp.getEmployee(str[1], al));
				al.add(e);
			}
			else {
				SalesEmployee e = new SalesEmployee(Integer.parseInt(str[3]), str[1], str[2], str[4], createDate(str[5]), str[6], str[7], Double.parseDouble(str[8]), createDate(str[9]), d.findDepartment(str[10], al), Double.parseDouble(str[11]), prdAl);
				al.add(e);
			}
		}
	}
	
	public static void printOut (ArrayList<Object> al) throws FileNotFoundException { //This method creates and fills the output.txt file
		PrintWriter output = new PrintWriter("output.txt");
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) instanceof Manager) {
				Manager m = (Manager)al.get(i);
				output.println(m.toString());
			}
		}
		output.println("\n\n********************************CUSTOMERS********************************");
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) instanceof Customer) {
				output.println(al.get(i).toString());
			}
		}
		
		output.println("\n**********************************PEOPLES**********************************");
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) instanceof Person && !(al.get(i) instanceof Employee) && !(al.get(i) instanceof Customer)) {
				output.println(al.get(i).toString());
			}
		}

		output.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
