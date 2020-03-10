
public abstract class SmartObject {

	private String alias;
	private String macId;
	private String IP;
	private boolean connectionStatus;
	
	SmartObject () {
	}
	
	public boolean connect (String IP) { //This method connects the object to the system.
		this.setIP(IP);
		if (!this.connectionStatus) {
			this.setConnectionStatus(true);
			System.out.println(this.getAlias() + " connection established");
			return true;
		}
		else {
			System.out.println("Device already connected!");
			return false;
		}
		
	}
	
	public boolean disconnect () {
		if (this.connectionStatus) {
			this.setConnectionStatus(false);
			this.setIP("");
			return true;
		}
		else {
			System.out.println("Device already disconnected!");
			return false;
		}
		
	}
	
	public void SmartObjectToString () {
		System.out.println("This is " + this.getClass() + " device " + this.getAlias() + 
					"\n\tMacID : " + this.getMacId() + "\n\tIP : " + this.getIP());
	}
	
	public boolean controlConnection () {
		if (!this.connectionStatus) {
			System.out.println("This device is not connected. " + this.getClass() + " -> " + this.getAlias());
			return false;
		}
		return true;
	}
	
	public abstract boolean testObject ();
	
	public abstract boolean shutDownObject ();

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public boolean isConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
}

