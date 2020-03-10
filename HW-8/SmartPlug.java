import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SmartPlug extends SmartObject implements Programmable {

	private boolean status;
	private Calendar programTime;
	private boolean programAction;
	
	SmartPlug (String alias, String macId) {
		this.setAlias(alias);
		this.setMacId(macId);
	}

	@Override
	public void setTimer(int seconds) {
		if (this.isConnectionStatus()) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, seconds);
			this.setProgramTime(cal);
			
				DateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
				Calendar cal2 = Calendar.getInstance();
				
				if (this.isStatus())
					System.out.println("Smart Plug - " + this.getAlias() + " will be turned off " + seconds + " seconds later! (Current time: " + currentTime.format(cal2.getTime()) + ")");
				else
					System.out.println("Smart Plug - " + this.getAlias() + " will be turned on " + seconds + " seconds later! (Current time: " + currentTime.format(cal2.getTime()) + ")");

		}
	}

	@Override
	public void cancelTimer() {
		if (this.isConnectionStatus())
			programTime = null;
	}

	@Override
	public void runProgram() {
		Calendar current = Calendar.getInstance();
		if (this.isConnectionStatus() && this.getProgramTime() != null && this.getProgramTime().get(11) == current.get(11) && this.getProgramTime().get(12) == current.get(12) && this.getProgramTime().get(13) == current.get(13)) {
			System.out.println("RunProgram -> SmartPlug - " + this.getAlias());
			
			if (this.isStatus())
				this.turnOff();
			else
				this.turnOn();
			
			this.setProgramTime(null);
		}
	}

	@Override
	public boolean testObject() {
		if (this.isConnectionStatus()) {
			this.SmartObjectToString();
			this.turnOn();
			this.turnOff();
			System.out.println("Test completed for SmartPlug");
				return true;
		}
		
				return false;
	}

	@Override
	public boolean shutDownObject() {
		if (this.isConnectionStatus()) {
			this.SmartObjectToString();
			this.turnOff();
				return true;
		}
		
				return false;
	}
	
	public void turnOn () {
		if (!this.isStatus()) {
			DateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			this.setStatus(true);
			System.out.println("Smart Plug -" + this.getAlias() + " is turned on now (Current time: " + currentTime.format(cal.getTime()) + ")");
		}
		else
			System.out.println("Smart Plug -" + this.getAlias() + " has been already turned on");
		
	}
	
	public void turnOff () {
		if (this.isConnectionStatus()) {
			DateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			this.setStatus(false);
			System.out.println("Smart Plug -" + this.getAlias() + " is turned off now (Current time: " + currentTime.format(cal.getTime()) + ")");
		}
		else
			System.out.println("Smart Plug -" + this.getAlias() + " has been already turned off");
		
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Calendar getProgramTime() {
		return programTime;
	}

	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}

	public boolean isProgramAction() {
		return programAction;
	}

	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
	
}
