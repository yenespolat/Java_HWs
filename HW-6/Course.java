
public class Course /*extends RegisterInfo*/ {

	
	private String department;
	private String name;
	private int credits;
	private int prereqYear;
	private int maxEnrollment;
	private int reservedSeats;
	private Student[] studentList = new Student[0];
	private Student[] replacementList = new Student[0];;
	private Faculty instructor;
	
	Course () {
		
	}
	Course (String department, String name, int credits, int prereqYear, int maxEnrollment, int reservedSeats) {
		this.department = department;
		this.name = name;
		this.credits = credits;
		this.prereqYear = prereqYear;
		this.maxEnrollment = maxEnrollment;
		this.reservedSeats = reservedSeats;
	}
	
	public RegisterInfo registerCourse (Student std) { //Tries to register given student to the course.
		RegisterInfo registration = new RegisterInfo();
		int exist = -1;
		for (int i = 0; i < studentList.length; i++) { //*
			if (studentList[i].getName() == std.getName())
				exist = i;
		}
		for (int i = 0; i < studentList.length; i++) { //* These two loops checks if the given student already exists in lists or not.
			if (studentList[i].getName() == std.getName())
				exist = i;
		}
		
		if (exist != -1) { //if the given student already exists in the lists, this statement informs user.
			registration.setResult("Rejected"); registration.setRegisterMessage("Already exists in lists");
			System.out.println("Student " + std.getName() + " " + std.getSurname() + " already exists in course lists.");
			registration.setCourse(this);
		}
		else {
		
		Student[] temp = new Student[studentList.length];
		registration.setCourse(this);
		//The statements below checks the students and adds them to corresponding list by given rules.
		if (std.getYear() < this.prereqYear) {
			registration.setResult("REJECTED");
			registration.setRegisterMessage("REQUEST REJECTED - PREREQUISITE");
		}
		else if ((((double)studentList.length + this.reservedSeats) / this.maxEnrollment) > 0.7 && !std.getMajor().equals(this.department)) {
			registration.setResult("REJECTED");
			registration.setRegisterMessage("REQUEST REJECTED - QUOTA");
			temp = replacementList;
			replacementList = new Student[replacementList.length + 1]; //Creates new array with the size of current size + 1.
			System.arraycopy(temp, 0, replacementList, 0, replacementList.length - 1); //Copies previous arrays objects to the new one.
			replacementList[replacementList.length - 1] = std; //Adds new object to the last index of new array.
		}
		else if (studentList.length + this.reservedSeats == this.maxEnrollment && this.reservedSeats > 0) {
			
			registration.setResult("WAITING");
			registration.setRegisterMessage("REQUEST WAITING - REPLACEMENT LIST");
			temp = replacementList;
			replacementList = new Student[replacementList.length + 1];
			System.arraycopy(temp, 0, replacementList, 0, replacementList.length - 1);
			replacementList[replacementList.length - 1] = std;
		}
		else if (studentList.length + this.reservedSeats == this.maxEnrollment && this.reservedSeats == 0) {
			registration.setResult("REJECTED");
			registration.setRegisterMessage("REQUEST REJECTED - QUOTA");
			temp = replacementList;
			replacementList = new Student[replacementList.length + 1];
			System.arraycopy(temp, 0, replacementList, 0, replacementList.length - 1);
			replacementList[replacementList.length - 1] = std;
		}
		else {
			
			registration.setResult("APPROVED");
			registration.setRegisterMessage("REQUEST APPROVED");
			registration.setRegisterID(studentList.length + 1);
			
			temp = studentList;
			studentList = new Student[studentList.length + 1];
			System.arraycopy(temp, 0, studentList, 0, studentList.length - 1);
			studentList[studentList.length - 1] = std;
			
		}
		
		std.addRegisterInfo(registration);
		
		}
		
		return registration;
	}
	
	public AssignInfo assignInstructor (Faculty instructor, boolean force) {
		
		AssignInfo assignment = new AssignInfo();
		assignment.setCourse(this);
		//The statements below assigns given instructor to the course by rules.
		if (this.getInstructor() != null && force && instructor.getDepartmentName().equals(this.department)) {
			assignment.setAssignResult("APPROVED");
			this.setInstructor(instructor);
			assignment.setAssignMessage("INSTRUCTOR " + this.instructor.getName().toUpperCase() + " ASSIGNED");
			
		}
		else if (this.getInstructor() == null && instructor.getDepartmentName().equals(this.department)) {
			assignment.setAssignResult("APPROVED");
			this.setInstructor(instructor);
			assignment.setAssignMessage("INSTRUCTOR " + this.instructor.getName().toUpperCase() + " ASSIGNED");
			
			
		}
		else if (!instructor.getDepartmentName().equals(this.department)) {
			assignment.setAssignResult("REJECTED");
			assignment.setAssignMessage("DEPARTMENT MISMATCH");
			
			
		}
		else if (this.getInstructor() != null && !force) {
			assignment.setAssignResult("REJECTED");
			assignment.setAssignMessage("ANOTHER INSTRUCTOR HAS ALREADY ASSIGNED");
			
		}
		
		instructor.addAssignInfo(assignment);
		
		return assignment;
	}
	
	public void registerReplacementList () {
		if (this.instructor != null && this.getReservedSeats() > 0) { //Checks if an instructor assigned to this course or not.
			//First registers the students whose major's matches with the department of course.
			for (int i = 0; i < this.getReplacementList().length && replacementList.length >= 1; i++) { 
				if (this.replacementList[i].getMajor().equals(this.getDepartment()) && this.getReservedSeats() > 0) {
					Student[] temp = new Student[studentList.length];
					temp = studentList;
					studentList = new Student[studentList.length + 1];
					System.arraycopy(temp, 0, studentList, 0, studentList.length - 1);
					studentList[studentList.length - 1] = this.getReplacementList()[i];
					this.getStudentList()[studentList.length - 1].changeRegisterInfo(this,"APPROVED","REQUEST APPROVED");
					Student[] temprr = new Student[this.getReplacementList().length];
					temprr = replacementList;
					replacementList = new Student[replacementList.length - 1];
					System.arraycopy(temprr, 1, replacementList, 0, temprr.length - 1);
					this.reservedSeats--;
				}
				
			}
		
			//Then registers the remaining students until the reserved seats all filled.
			for (int i = 0; i < this.getReplacementList().length; i++) {
				while (this.getReservedSeats() > 0 && replacementList.length >= 1) {
					Student[] temp = new Student[studentList.length];
					temp = studentList;
					studentList = new Student[studentList.length + 1];
					System.arraycopy(temp, 0, studentList, 0, studentList.length - 1);
					studentList[studentList.length - 1] = this.getReplacementList()[i];
					this.getStudentList()[studentList.length - 1].changeRegisterInfo(this,"APPROVED","REQUEST APPROVED");
					Student[] temprr = new Student[this.getReplacementList().length];
					temprr = replacementList;
					replacementList = new Student[replacementList.length - 1];
					System.arraycopy(temprr, 1, replacementList, 0, temprr.length - 1);
					this.reservedSeats--;
				}
			}
	}
		else
			System.out.println("It should be an instructor assigned to this course first!");
		
	}
	
	public void printStudentList () {
		System.out.println("COURSE : " + this.getName() + " DEPARTMENT : " + this.getDepartment());
		if (this.studentList.length !=0) {
			for (int i = 0; i < this.studentList.length; i++) {
				System.out.println("\tStudent ID : " + studentList[i].getID() + " Name : " + studentList[i].getName() + "\t");
			}
			
		}
		else
			System.out.println("THERE IS NO STUDENT REGISTERED TO THIS COURSE");
		if (this.replacementList.length != 0) {
			for (int i = 0; i < this.replacementList.length; i++) {
				System.out.println("\tStudent ID : " + replacementList[i].getID() + " Name : " + replacementList[i].getName() + "\t");
			}
		}
		else
			System.out.println("NO STUDENT IN REPLACEMENT LIST");

	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getPrereqYear() {
		return prereqYear;
	}

	public void setPrereqYear(int prereqYear) {
		this.prereqYear = prereqYear;
	}

	public int getMaxEnrollment() {
		return maxEnrollment;
	}

	public void setMaxEnrollment(int maxEnrollment) {
		this.maxEnrollment = maxEnrollment;
	}

	public int getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public Student[] getStudentList() {
		return studentList;
	}

	public void setStudentList(Student[] studentList) {
		this.studentList = studentList;
	}

	public Student[] getReplacementList() {
		return replacementList;
	}

	public void setReplacementList(Student[] replacementList) {
		this.replacementList = replacementList;
	}

	public Faculty getInstructor() {
		return instructor;
	}

	public void setInstructor(Faculty instructor) {
		this.instructor = instructor;
	}


	
	
	
	
	
}
