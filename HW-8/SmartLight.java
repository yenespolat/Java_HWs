import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SmartLight extends SmartObject implements Programmable, LocationControl {

	private boolean hasLightTurned;
	private Calendar programTime;
	private boolean programAction;
	
	SmartLight(String alias, String macId) {
		this.setAlias(alias);
		this.setMacId(macId);
	}

	@Override
	public boolean testObject() { //This method simply tests the object, turns it on and off.
		
		if (this.isConnectionStatus()) {
			this.SmartObjectToString();
			this.turnOnLight();
			this.turnOffLight();
			System.out.println("Test completed for SmartLight");
				return true;
		}
		
				return false;
	}

	@Override
	public boolean shutDownObject() { //This method shuts the object down, disconnects it.
		if (this.isConnectionStatus()) {
			this.SmartObjectToString();
			this.turnOffLight();
				return true;
		}
		
				return false;
	}
	
	public void turnOnLight () {
		if (!this.isHasLightTurned()) {
			DateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			
			this.setHasLightTurned(true);
			System.out.println("Smart Light -" + this.getAlias() + " is turned on now (Current time: " + currentTime.format(cal.getTime()) + ")");
		}
		else
			System.out.println("Smart Light -" + this.getAlias() + " has been already turned on");
		
	}
	
	public void turnOffLight () {
		if (this.isConnectionStatus()) {
			DateFormat currentTime = new SimpleDateFormat("HH:mm:ss"); //This formats the date string.
			Calendar cal = Calendar.getInstance();
			
			this.setHasLightTurned(false);
			System.out.println("Smart Light -" + this.getAlias() + " is turned off now (Current time: " + currentTime.format(cal.getTime()) + ")");
		}
		else
			System.out.println("Smart Light -" + this.getAlias() + " has been already turned off");
		
	}

	@Override
	public void setTimer(int seconds) {
		if (this.isConnectionStatus()) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, seconds); // This adds the given seconds value to the current time.
			this.setProgramTime(cal);
			
				DateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
				Calendar cal2 = Calendar.getInstance();
				
				if (this.isHasLightTurned())
					System.out.println("Smart Light - " + this.getAlias() + " will be turned off " + seconds + " seconds later! (Current time: " + currentTime.format(cal2.getTime()) + ")");
				else
					System.out.println("Smart Light - " + this.getAlias() + " will be turned on " + seconds + " seconds later! (Current time: " + currentTime.format(cal2.getTime()) + ")");

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
		if (this.isConnectionStatus() && this.getProgramTime()!= null && this.getProgramTime().get(11) == current.get(11) && this.getProgramTime().get(12) == current.get(12) && this.getProgramTime().get(13) == current.get(13)) {
			System.out.println("RunProgram -> SmartLight - " + this.getAlias());
			
			if (this.hasLightTurned)
				this.turnOffLight();
			else
				this.turnOnLight();
			
			this.setProgramTime(null);
		}
	}

	@Override
	public void onLeave() {
		if (this.isConnectionStatus() && this.isHasLightTurned()) {
			System.out.println("On Leave -> SmartLight - " + this.getAlias());
			this.turnOffLight();
		}
		
	}

	@Override
	public void onCome() {
		if (this.isConnectionStatus() && !this.isHasLightTurned()) {
			System.out.println("On Come -> SmartLight - " + this.getAlias());
			this.turnOnLight();
		}
	}

	public boolean isHasLightTurned() {
		return hasLightTurned;
	}

	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
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
