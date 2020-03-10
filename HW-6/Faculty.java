
public class Faculty {

	private int ID;
	private String name;
	private String surname;
	private String departmentName;
	private AssignInfo[] assignInfoList = new AssignInfo[0];
	
	Faculty () {
		
	}
	
	Faculty (int id, String name, String surname, String departmentName) {
		this.ID = id;
		this.name = name;
		this.setSurname(surname);
		this.departmentName = departmentName;
	}
	
	public boolean withdrawAssignInfo (Course course) {
		int removeIndex = -1; 
		for (int i = 0; i < assignInfoList.length; i++) { //Finds the index of given course at assign info list.
			if (assignInfoList[i].getCourse().getName() == course.getName()) {
				removeIndex = i;
			}
		}
		if (removeIndex != -1) {
			//The statements below creates an array with -1 size of the current array and copies the previous arrays objects to the new one except index 'removeIndex' one.
			AssignInfo[] temp = new AssignInfo[assignInfoList.length - 1];
			for (int i = 0; i < temp.length; i++) {
				if (i < removeIndex) 
					temp[i] = assignInfoList[i];
				else if (removeIndex == -1)
					break;
				else if (i > removeIndex)
					temp[i-1] = assignInfoList[i];
			}
			assignInfoList = temp;
			return true;
		}
		else
			System.out.println("Course " + course.getName() + " not exist on this instructors list.");
	
		return false;
	}
	
	public void addAssignInfo ( AssignInfo assignInfo) {
		AssignInfo[] temp = new AssignInfo[assignInfoList.length];
		temp = assignInfoList;
		assignInfoList = new AssignInfo[assignInfoList.length + 1];
		System.arraycopy(temp, 0, assignInfoList, 0, assignInfoList.length - 1);
		assignInfoList[assignInfoList.length - 1] = assignInfo;
	}
	
	public void printCourseList () {
		System.out.println("Instructor : " + this.getName() + " " + this.getSurname() + " Department : " + this.getDepartmentName());
		for (int i = 0; i < this.assignInfoList.length; i++) {
			System.out.println("\tCourse : " + assignInfoList[i].getCourse().getName() + "\t");
		}
		System.out.println();
	}
	

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public AssignInfo[] getAssignInfoList() {
		return assignInfoList;
	}

	public void setAssignInfoList(AssignInfo[] assignInfoList) {
		this.assignInfoList = assignInfoList;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	
}
