import java.util.ArrayList;
import java.util.Calendar;

public class RegularEmployee extends Employee{

	private double performanceScore;
	private double bonus;
	
	public RegularEmployee (int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hiredate, Department department, double performanceScore) throws Exception {
		
		super (id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hiredate, department);
		this.performanceScore = performanceScore;
		
	}
	
	public RegularEmployee (Employee e, double performanceScore) throws Exception {
		super(e.getId(), e.getFirstName(), e.getLastName(), e.getGender(), e.getBirthDate(), e.getMaritalStatus(), e.getHasDriverLicence(), e.getSalary(), e.getHireDate(), e.getDepartment());
		this.performanceScore = performanceScore;
	}
	
	public RegularEmployee () {
		
	}

	public double getPerformanceScore() {
		return performanceScore;
	}

	public void setPerformanceScore(double performanceScore) {
		this.performanceScore = performanceScore;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double unit) {
		this.bonus = (int)(unit * this.getSalary() * this.getPerformanceScore() * 100) /100.0;
	}
	
	public RegularEmployee getRegularEmployee (String str, ArrayList<Object> al) { //This method finds and returns the RegularEmployee object with the given id string and arraylist.
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) instanceof RegularEmployee) {
				RegularEmployee e = (RegularEmployee)(al.get(i));
				if (e.getId() == Integer.parseInt(str))
					return (RegularEmployee)(al.get(i));
			}
		}
		return null;
	}
	
	public String toString() {
		return "\t\t\tRegularEmployee\n"	+ "\t\t\t\t" + "Person Info [id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", gender=" + this.getGender() + "]" + 
				"\n\t\t\t\t" + "Employee Info [salary=" + this.getSalary() + ", hireDate=" + this.stringCalendar() + "]" + 
				"\n\t\t\t\t" + "Regular Employee Info [performansScore=" + this.getPerformanceScore() + ", bonus=" + this.getBonus() + "]";
	}
	
}
