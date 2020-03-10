import java.util.ArrayList;
import java.util.Calendar;

public class SalesEmployee extends RegularEmployee{
	
	public static int numberofSalesEmployees;
	private ArrayList<Product> sales;
	
	public SalesEmployee (int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hiredate, Department department, double pScore, ArrayList<Product> s) throws Exception {
		super (id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hiredate, department, pScore);
		this.sales = s;
		numberofSalesEmployees++;
	}
	
	public SalesEmployee (RegularEmployee r, ArrayList<Product> p) throws Exception {
		super(r.getId(), r.getFirstName(), r.getLastName(), r.getGender(), r.getBirthDate(), r.getMaritalStatus(), r.getHasDriverLicence(), r.getSalary(), r.getHireDate(), r.getDepartment(), r.getPerformanceScore());
		this.sales = p;
		numberofSalesEmployees++;
	}
	
	public SalesEmployee () {
		
	}
	
	public boolean addSale (Product p) {
		sales.add(p);
		return true;
	}
	
	public boolean removeSale (Product p) {
		if (sales.contains(p)) {
			sales.remove(p);
			return true;
		}
		else return false;
	}

	public static int getNumberofSalesEmployees() {
		return numberofSalesEmployees;
	}

	public static void setNumberofSalesEmployees(int numberofSalesEmployees) {
		SalesEmployee.numberofSalesEmployees = numberofSalesEmployees;
	}

	public ArrayList<Product> getSales() {
		return sales;
	}

	public void setSales(ArrayList<Product> sales) {
		this.sales = sales;
	}
	
	@Override
	public String toString () {
		return "\t\t\tSalesEmployee\n"+ "\t\t\t\t" + "Person Info [id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", gender=" + this.getGender() + "]" + 
				"\n\t\t\t\t" + "Employee Info [salary=" + this.getSalary() + ", hireDate=" + this.stringCalendar() + "]" + 
				"\n\t\t\t\t" + "Regular Employee Info [performansScore=" + this.getPerformanceScore() + ", bonus=" + this.getBonus() + "]" + 
				"\n\t\t\t\t" + productString();
	}
	public String productString () {
		String s = "Product Info ";
		for (int i = 0; i < getSales().size(); i++) {
			s += getSales().get(i).toString();
			if (i < getSales().size() - 1)
				s += ", ";
		}
		return s;
	}
	

}
