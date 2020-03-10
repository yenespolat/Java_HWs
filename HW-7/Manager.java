import java.util.ArrayList;
import java.util.Calendar;

public class Manager extends Employee{

	private double bonusBudget;
	private ArrayList<RegularEmployee> regularEmployees = new ArrayList<RegularEmployee>();
	
	public Manager (int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hiredate, Department department, double bonusBudget) throws Exception {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hiredate, department);
		this.bonusBudget = bonusBudget;
	}
	
	public Manager () {
		
	}
	
	public Manager (Employee e, double bonusBudget) throws Exception {
		super(e.getId(), e.getFirstName(), e.getLastName(), e.getGender(), e.getBirthDate(), e.getMaritalStatus(), e.getHasDriverLicence(), e.getSalary(), e.getHireDate(), e.getDepartment());
		this.bonusBudget = bonusBudget;
	}
	
	public void addEmployee (RegularEmployee e) {
		regularEmployees.add(e);
	}
	
	public void removeEmployee (RegularEmployee e) {
		regularEmployees.remove(e);
	}
	
	public void distributeBonusBudget() {
		double sum = 0;
		for (int i = 0; i < regularEmployees.size(); i++) {
			sum += regularEmployees.get(i).getSalary() * regularEmployees.get(i).getPerformanceScore();
		}
		double unit = this.bonusBudget / sum;
		for (int i = 0; i < regularEmployees.size(); i++) {
			regularEmployees.get(i).setBonus(unit);
		}
	}

	public double getBonusBudget() {
		return bonusBudget;
	}

	public void setBonusBudget(double bonusBudget) {
		this.bonusBudget = bonusBudget;
	}

	public ArrayList<RegularEmployee> getRegularEmployees() {
		return regularEmployees;
	}

	public void setRegularEmployees(ArrayList<RegularEmployee> regularEmployees) {
		this.regularEmployees = regularEmployees;
	}
	
	public String toString() {
		String s = this.getDepartment().toString();
		s += "\tManager [id=" + this.getId() + ", " + this.getFirstName() + " " + this.getLastName();
		s += "\n\t\t# of Employees=" + this.getRegularEmployees().size() + "]\n";
		for (int f = 0; f < this.getRegularEmployees().size(); f++) {
			s+= this.getRegularEmployees().get(f).toString() + "\n";					
		}
		return s;
	}
	
	
	
}
