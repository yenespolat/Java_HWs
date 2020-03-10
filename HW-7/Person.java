import java.util.ArrayList;
import java.util.Calendar;
//import java.util.InputMismatchException;
import java.util.Scanner;
public class Person {

	private int id;
	private String firstName;
	private String lastName;
	private byte gender = 0;
	private byte maritalStatus;
	private boolean hasDriverLisence;
	private Calendar birthDate;
	Scanner input = new Scanner(System.in);
	
	public Person () {
		
	}
	public Person (int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence) throws Exception {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		setGender(gender);
		this.birthDate = birthDate;
		setMaritalStatus(maritalStatus);
		setHasDriverLicence(hasDriverLicence);		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) throws Exception {
		if(id>=0)
			this.id = id;
		else throw new Exception ("ID must bigger than zero!");
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws Exception {
		if (firstName.length()>=3)
			this.firstName = firstName;
		else throw new Exception ("No less than 3 letters in name field!");
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) throws Exception {
		if (lastName.length()>=3)
			this.lastName = lastName;
		else throw new Exception ("No less than 3 letters in name field!");
	}
	public Calendar getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}
	public void setGender (String gender) throws Exception {
		
		if (gender.equalsIgnoreCase("Man"))
			this.gender = 1;
		else if (gender.equalsIgnoreCase("Woman"))
			this.gender = 2;
		else {
			throw new Exception("The gender value must be either 'Man' or 'Woman'");
			/*while (!(gender.equalsIgnoreCase("man") || gender.equalsIgnoreCase("Woman"))) {
				System.out.print("Enter 'Man' or 'Woman' : ");
				gender = input.next();
					if (gender.equalsIgnoreCase("Man"))
						this.gender = 1;
					else if (gender.equalsIgnoreCase("Woman"))
						this.gender = 2;*/
			
		}
			
	}
	public String getGender () {
		if (this.gender == 1)
			return "Man";
		else if (this.gender == 2)
			return "Woman";
		else
			return "Not set yet";
	}
	public void setMaritalStatus (String status) throws Exception {
		if (status.equalsIgnoreCase("Single"))
			this.maritalStatus = 1;
		else if (status.equalsIgnoreCase("Married"))
			this.maritalStatus = 2;
		else {
			throw new Exception("Marital status must be either 'Single' or 'Married'.");
		}
	}
	public String getMaritalStatus () {
		if (this.maritalStatus == 1)
			return "Single";
		else if (this.maritalStatus == 2)
			return "Married";
		else
			return "Not set yet";
		
	}
	public void setHasDriverLicence (String info) throws Exception {
		if (info.equalsIgnoreCase("Yes"))
			this.hasDriverLisence = true;
		else if (info.equalsIgnoreCase("No"))
			this.hasDriverLisence = false;
		else {
			throw new Exception("Driver licence value must be either 'Yes' or 'No'.");
		}
	}
	public String getHasDriverLicence () {
		if (this.hasDriverLisence == true)
			return "Yes";
		else if (this.hasDriverLisence == false)
			return "No";
		else
			return "Not set yet";
	}
	
	public Person getPerson (String str, ArrayList<Object> al) {
		Person d;
		Person p = new Person ();
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) instanceof Person) {
				d = (Person)(al.get(i));
				if (d.getId() == Integer.parseInt(str)) {
					p = d;
				}
			}
		}
		return p;
	}
	
	public String stringCalendar () {
		return this.getBirthDate().getTime().toString();
	}
	
	@Override
	public String toString () {
		return "Person " + "[id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", gender=" + this.getGender() + ", birthDate=" + this.stringCalendar() + ", maritalStatus=" + this.getMaritalStatus() + ", hasDriverLicence=" + this.getHasDriverLicence() + "]";
	}
}
