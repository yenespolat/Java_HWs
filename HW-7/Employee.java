import java.util.ArrayList;
import java.util.Calendar;

public class Employee extends Person{

	private double salary;
	private Calendar hireDate;
	private Department department;
	public static int numberofEmployees;
	
	public Employee () {
		
	}
	public Employee (int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hiredate, Department department) throws Exception {
		super( id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
		setSalary(salary);
		this.hireDate = hiredate;
		this.department = department;
		numberofEmployees++;
	}
	
	public Employee (Person person, double salary, Calendar hireDate, Department department) throws Exception {
		super(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), person.getBirthDate(),person.getMaritalStatus(), person.getHasDriverLicence());
		setSalary(salary);
		this.hireDate = hireDate;
		this.department = department;
		numberofEmployees++;
		
	}
	
	public double raiseSalary (double percent) throws Exception {
		if (percent <= 1 && percent >= 0) {
			this.salary *= (1 + percent);
			return this.salary; 
		}
		else {
			throw new Exception("Percentage value should be between 0 and 1!");
		}
	}
	
	public double raiseSalary (int amount) {
		this.salary += amount;
			return this.salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) throws Exception{
		if (salary >= 0)
			this.salary = salary;
		else
			throw new Exception("Salary must be bigger than zero!");
	}

	public Calendar getHireDate() {
		return hireDate;
	}

	public void setHireDate(Calendar hireDate) {
		this.hireDate = hireDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static int getNumberofEmployees() {
		return numberofEmployees;
	}

	public static void setNumberofEmployees(int numberofEmployees) {
		Employee.numberofEmployees = numberofEmployees;
	}
	
	public String stringCalendar () {
		return this.getHireDate().getTime().toString();
	}
	
	public Employee getEmployee (String str, ArrayList<Object> al) {
		Employee e;
		Employee f = new Employee ();
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) instanceof Employee) {
				e = (Employee)(al.get(i));
				if (e.getId() == Integer.parseInt(str)) {
					f = e;				
				}
			}
		}
		return f;
		
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + this.getId() + ", salary=" + this.getSalary() + ", hireDate=" + this.stringCalendar() + ", department=" + this.getDepartment().getDepartmentName() + "]";
	}
}
