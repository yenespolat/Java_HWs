import java.util.ArrayList;

public class Department {

	private int departmentId;
	private String departmentName;
	
	public Department () {
		
	}
	
	public Department (int id, String name) {
		this.departmentId = id;
		this.departmentName = name;
	}
	
	public Department getDepartment () {
		return this;
	}
	
	public Department findDepartment (String str, ArrayList<Object> al) {
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) instanceof Department) {
				Department d = (Department)(al.get(i));
				if (d.getDepartmentName().equalsIgnoreCase(str))
					return (Department)(al.get(i));
			}
		}
		return null;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String toString () {
		String s = "**************************************************************************************\n";
		s += "Department [departmentId=" + this.getDepartment().getDepartmentId() + ", departmentName=" + this.getDepartment().getDepartmentName() + "]\n";
		return s;
	}
	
}
