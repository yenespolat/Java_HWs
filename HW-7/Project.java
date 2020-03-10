import java.util.Calendar;

public class Project {

	private String projectName;
	private Calendar startDate;
	private boolean state;
	
	public Project () {
		
	}
	
	public Project (String name, Calendar date, String state) throws Exception {
		setProjectName(name);
		this.startDate = date;
		setState(state);
	}
	
	public void setState (String s) throws Exception {
		if (s.equalsIgnoreCase("open"))
			this.state = true;
		else if (s.equalsIgnoreCase("close"))
			this.state = false;
		else throw new Exception ("The state value must be either 'open' or 'close'.");
	}
	
	public String getState () {
		if (this.state)
			return "Open";
		else
			return "Close";
	}
	
	public void close () throws Exception {
		setState("close");
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) throws Exception {
		if (projectName.length()>=3)
			this.projectName = projectName;
		else throw new Exception ("Letter number in name of projects must bigger than 2!");
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	
	public String stringCalendar () {
		return this.getStartDate().getTime().toString();
	}
	
	public String toString() {
		return "[projectName=" + this.getProjectName() + ", startDate=" + this.stringCalendar() + ", state=" + this.getState() + "]";
	}
	
	
}
