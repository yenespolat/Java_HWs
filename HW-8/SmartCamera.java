
public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera> {

	private boolean status;
	private int batteryLife;
	private boolean nightVision;
	
	

	SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
		this.setAlias(alias);
		this.setMacId(macId);
		this.setNightVision(nightVision);
		this.setBatteryLife(batteryLife);
	}

	@Override
	public boolean controlMotion(boolean hasMotion, boolean isDay) {
		
		
		if (hasMotion)
			System.out.println("Motion detected!");
		else
			System.out.println("Motion not detected!");
		
		if (isDay) {
			recordOn(isDay);
			return true;
		}
		else
			if (this.isNightVision()) {
				recordOn(isDay);
				return true;
			}
		
		return false;
	}

	@Override
	public boolean testObject() {
		if (this.isConnectionStatus()) {
			this.SmartObjectToString();
			System.out.println("Test is starting for SmartCamera day time");
			this.recordOn(true);
			this.recordOff();
			System.out.println("Test is starting for SmartCamera night time");
			this.recordOn(false);
			this.recordOff();
			System.out.println("Test completed for SmartCamera");
				return true;
		}
		
				return false;
	}

	@Override
	public boolean shutDownObject() {
		if (this.isConnectionStatus()) {
			this.SmartObjectToString();
			this.recordOff();
				return true;
		}
		
				return false;
	}
	
	public void recordOn (boolean isDay) { //After invoking this method, camera will start to record.
		if (this.isConnectionStatus())
			if (!this.isStatus() && !this.isNightVision() && !isDay)
				System.out.println("Sorry! Smart Camera - " + this.getAlias() + " does not have night vision feature.");
			else if (!this.isStatus()) {
				System.out.println("Smart Camera - " + this.getAlias() + " is turned on now");
				this.setStatus(true);
			}
			else if (this.isStatus())
				System.out.println("Smart Camera - " + this.getAlias() + " has been already turned on");
			
	}
	
	public void recordOff () {
		if (this.isConnectionStatus()) {
			if (this.isStatus()) {
				System.out.println("Smart Camera - " + this.getAlias() + " is turned off now");
				this.setStatus(false);
			}
			else
				System.out.println("Smart Camera - " + this.getAlias() + " has been already turned off");
			
		}
		
	}
	
	public String toString () {
		String statusStr = "";
		if (this.isStatus())
			statusStr += "recording";
		else
			statusStr += "not recording";
		
		return "Smart Camera -> " + this.getAlias() + "'s battery life is " + this.getBatteryLife() + " status is " + statusStr;
	}

	@Override
	public int compareTo(SmartCamera o) { //This method is used for sorting cameras.
		
		if (o.getBatteryLife() > this.getBatteryLife())
			return -1;
		else if (o.getBatteryLife() < this.getBatteryLife())
			return 1;
		else
			return 0;
		
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public boolean isNightVision() {
		return nightVision;
	}

	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}
	
}
