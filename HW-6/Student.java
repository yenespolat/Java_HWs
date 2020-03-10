
public class Student /*extends RegisterInfo*/{

	private String name = "";
	private String surname = "";
	private String major = "";
	private int year = 0;
	private int ID = 0;
	private RegisterInfo[] registerList = new RegisterInfo[0];
	

	Student () {
		
	}
	Student (String name, String surname, String major, int ID, int year) {
	
		this.name = name;
		this.surname = surname;
		this.major = major;
		this.year = year;
		this.ID = ID;
		
	}
	
	public void changeRegisterInfo(Course course, String result, String message) {
		int address = -1;
		for (int i = 0; i < this.registerList.length; i++) { //Checks existence of entered course.
			if (registerList[i].getCourse().equals(course)) {
				address = i;
				continue;
			}
		}
		if (address != -1) {
		registerList[address].setResult(result);
		registerList[address].setRegisterMessage(message);
		}
		else
			System.out.println("Not Changed (There isn't any course named " + course.getName() + ")");
	}
	
	public void addRegisterInfo (RegisterInfo registerInfo) {
		RegisterInfo[] temp = new RegisterInfo[registerList.length];
		temp = registerList;
		registerList = new RegisterInfo[registerList.length + 1]; //Creates new array with the size of current size + 1.
		System.arraycopy(temp, 0, registerList, 0, registerList.length - 1); //Copies previous arrays objects to the new one.
		registerList[registerList.length - 1] = registerInfo; //Adds new object to the last index of new array.
		
	}
	
	public boolean removeRegisterInfo (Course course) {
		int removeIndex = -1;
		
		for (int i = 0; i < registerList.length; i++) { //Checks existence of entered course.
			if (registerList[i].getCourse().getName().equals(course.getName())) {
				removeIndex = i;
			}
		}
		RegisterInfo[] temp = new RegisterInfo[registerList.length - 1]; //Creates temporary array with size of (current register list size - 1).
		if (removeIndex == -1) //if there isn't course such user wants to remove, method returns false.
			return false;
		else {
			for (int i = 0; i < temp.length; i++) { //Copies bigger arrays objects to the temporary one except index of 'removeIndex'.
				if (i < removeIndex) 
					temp[i] = registerList[i];
				else if (i > removeIndex)
					temp[i-1] = registerList[i];
			}
			registerList = temp;
			return true;
		}
	}
	
	public void printCourseList() { //Prints all register info of courses that student attempted to join. Even waiting and rejected ones.
		System.out.println("STUDENT : " + this.getName() + " MAJOR : " + this.getMajor());
		for (int i = 0; i < this.registerList.length; i++) {
			System.out.println("\tCOURSE : " + registerList[i].getCourse().getName() + " STATUS : " + registerList[i].getRegisterMessage());
		}
		System.out.println("\tTotal Credits : " + this.getTotalCredit());
		System.out.println();
		
	}
	
	public int getTotalCredit() {
		int credit = 0;
		for (int i = 0; i < this.registerList.length; i++) {
			if (registerList[i].getResult().equals("APPROVED") ||registerList[i].getResult().equals("WAITING")) {
			credit += registerList[i].getCourse().getCredits();
			}
		}
		
		return credit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
}
