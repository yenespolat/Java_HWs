import java.util.ArrayList;
import java.util.Calendar;

public class Developer extends RegularEmployee{

	public static int numberofDevelopers;
	private ArrayList<Project> projects;
	
	public Developer (int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hiredate, Department department, double pScore, ArrayList<Project> s) throws Exception {
		super (id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hiredate, department, pScore);
		this.projects = s;
		numberofDevelopers++;
	}
	
	public Developer () {
		
	}
	
	public Developer (RegularEmployee e, ArrayList<Project> p) throws Exception {
		super(e.getId(), e.getFirstName(), e.getLastName(), e.getGender(), e.getBirthDate(), e.getMaritalStatus(), e.getHasDriverLicence(), e.getSalary(), e.getHireDate(), e.getDepartment(), e.getPerformanceScore());
		this.projects = p;
		numberofDevelopers++;
	}
	
	public boolean addProject (Project p) {
		projects.add(p);
		return true;
	}
	
	public boolean removeProject (Project p) {
		if (projects.contains(p)) {
			projects.remove(p);
			return true;
		}
		else return false;
	}

	public static int getNumberofDevelopers() {
		return numberofDevelopers;
	}

	public static void setNumberofDevelopers(int numberofDevelopers) {
		Developer.numberofDevelopers = numberofDevelopers;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}
	
	@Override
	public String toString () {
		return  "\t\t\tDeveloper\n"	+ "\t\t\t\t" + "Person Info [id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", gender=" + this.getGender() + "]" + 
				"\n\t\t\t\t" + "Employee Info [salary=" + this.getSalary() + ", hireDate=" + this.stringCalendar() + "]" + 
				"\n\t\t\t\t" + "Regular Employee Info [performansScore=" + this.getPerformanceScore() + ", bonus=" + this.getBonus() + "]" + 
				"\n\t\t\t\t" + projectString();
	}
	
	public String projectString () { //This method and similar one in the Product class simply creates the string with objects in arraylist.
		String s = "Project Info ";
		for (int i = 0; i < getProjects().size(); i++) {
			s += getProjects().get(i).toString();
			if (i < getProjects().size() - 1)
				s += ", ";
		}
		return s;
	}
	
	
	
	
}
