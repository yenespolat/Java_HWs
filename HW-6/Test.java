// 150117015 Yasin Enes Polat
/* 
This program  creates new courses, students, and instructors.
Then registers students to the courses , assigns instructors to the courses and moves the
students in the replacements list of a course to the registered list once an instructor has
been assigned to the course by the given rules.
*/
 
public class Test {

	public static void main(String[] args) {

		// The following part creates several student objects in order of first name, last name, major, student ID, year,
		Student ayse = new Student ("Ayse", "Caliskan", "Computer Engineering", 150180001, 4);
		Student mehmet = new Student ("Mehmet", "Yilmaz", "Computer Engineering", 150180088, 3);
		Student ali = new Student ("Ali", "Ozdemir", "English", 450181112, 1);
		Student elif = new Student ("Elif", "Ozturk", "English", 450181145, 2);
		Student enes = new Student ("Yasin Enes", "Polat", "Computer Engineering", 150117015, 3);

		// The following part creates several faculty objects in order of instructor ID, first name, last name, department, office
		Faculty sanem = new Faculty (111, "Sanem", "Arslan Yilmaz", "Computer Engineering");
		Faculty fatma = new Faculty (222, "Fatma", "Corut Ergin", "Computer Engineering");
		Faculty ahmet = new Faculty (333, "Ahmet", "Taylan", "Deneme");

		// The following part creates several course objects in order of department, title, course credits, prereq year, max enrollment, current reserved seats
		Course cse1141 = new Course ("Computer Engineering", "CSE1141", 15, 1, 10, 9);
		Course eng4000 = new Course ("English", "ENG4000", 12, 2, 1, 0);
		Course deneme = new Course ("Deneme", "Deneme Kursu", 6, 1, 8, 2);
		
		// Add students to courses
		RegisterInfo reg1 = cse1141.registerCourse(ayse);
		System.out.println("Test -> Course : " + reg1.getCourse().getName() + "\tResult : " + reg1.getResult() + "\tMessage : " + reg1.getRegisterMessage());

		reg1 = cse1141.registerCourse(mehmet);
		System.out.println("Test -> Course : " + reg1.getCourse().getName() + "\tResult : " + reg1.getResult() + "\tMessage : " + reg1.getRegisterMessage());

		reg1 = eng4000.registerCourse(ali);
		System.out.println("Test -> Course : " + reg1.getCourse().getName() + "\tResult : " + reg1.getResult() + "\tMessage : " + reg1.getRegisterMessage());

		reg1 = eng4000.registerCourse(ayse);
		System.out.println("Test -> Course : " + reg1.getCourse().getName() + "\tResult : " + reg1.getResult() + "\tMessage : " + reg1.getRegisterMessage());

		reg1 = eng4000.registerCourse(elif);
		System.out.println("Test -> Course : " + reg1.getCourse().getName() + "\tResult : " + reg1.getResult() + "\tMessage : " + reg1.getRegisterMessage());

		reg1 = cse1141.registerCourse(ayse);
		System.out.println("Test -> Course : " + reg1.getCourse().getName() + "\tResult : " + reg1.getResult() + "\tMessage : " + reg1.getRegisterMessage());

		reg1 = deneme.registerCourse(enes);
		System.out.println("Test -> Course : " + reg1.getCourse().getName() + "\tResult : " + reg1.getResult() + "\tMessage : " + reg1.getRegisterMessage());

		reg1 = cse1141.registerCourse(enes);
		System.out.println("Test -> Course : " + reg1.getCourse().getName() + "\tResult : " + reg1.getResult() + "\tMessage : " + reg1.getRegisterMessage());

		System.out.println("-------------------------------------------------------------------------------------------");
		
		//Print the course lists of the students
		ayse.printCourseList();
		mehmet.printCourseList();
		ali.printCourseList();
		elif.printCourseList();
		enes.printCourseList();


		//Register students in replacement list to the courses
		cse1141.registerReplacementList();

		//Assign instructors to the courses
		AssignInfo ass1 = cse1141.assignInstructor(sanem, false); //Assign sanem to cse1141 without forcing
		System.out.println("Test -> Course : " + ass1.getCourse().getName() + " Result : " + ass1.getAssignResult() + " Message : " + ass1.getAssignMessage());

		System.out.println();

		ass1 = cse1141.assignInstructor(fatma, true); //Assign fatma to cse1141 by force
		System.out.println("Test -> Course : " + ass1.getCourse().getName() + " Result : " + ass1.getAssignResult() + " Message : " + ass1.getAssignMessage());
		System.out.println();
		
		ass1 = deneme.assignInstructor(ahmet, true); //Assign ahmet to deneme by force
		System.out.println("Test -> Course : " + ass1.getCourse().getName() + " Result : " + ass1.getAssignResult() + " Message : " + ass1.getAssignMessage());
		System.out.println();
		
		//Print the course lists of the instructors
		sanem.printCourseList();
		fatma.printCourseList();
		//Register students in replacement list to the course with instructor's decisions
		cse1141.registerReplacementList();

		//Print the course lists of the students
		mehmet.printCourseList();
		enes.printCourseList();
		
		cse1141.printStudentList();
		
		System.out.println(mehmet.removeRegisterInfo(deneme));
		

	}

}

