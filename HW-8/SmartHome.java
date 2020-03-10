import java.util.ArrayList;
import java.util.Arrays;

public class SmartHome {
	
	private ArrayList<SmartObject> smartObjectList = new ArrayList<SmartObject>();
	
	SmartHome() {
		
	}
	
	public boolean addSmartObject (SmartObject sO) { //This method adds the given object to smartObjectList.
		int ipindex = 100;
		String ip = "10.0.0." + (ipindex + this.getSmartObjectList().size()); //This string assigned to objects' ip variable.
		if (!this.getSmartObjectList().contains(sO)) {
			System.out.println("-------------------------------------------------------------"
					+ "\nAdding new SmartObject\n-------------------------------------------------------------");
			this.getSmartObjectList().add(sO);
			sO.connect(ip);
			sO.testObject();
			return true;
		}
		
		return false;
	}
	
	public boolean removeSmartObject (SmartObject sO) { //This method removes the given object to smartObjectList.
		
		if (this.getSmartObjectList().contains(sO)) {
			this.getSmartObjectList().remove(sO);
			return true;
		}
			
		return false;
	}
	
	public void controlLocation(boolean onCome) {
		System.out.println("-------------------------------------------------\n"
				+ "LocationControl : OnCome" + "\n-------------------------------------------------");
		for (int i = 0; i < getSmartObjectList().size(); i++) { //This loop checks if the ith index of getSmartObjectList is an instance of LocationControl or not, then invokes the given methods of objects.
			if (getSmartObjectList().get(i) instanceof LocationControl) {
				if (onCome)
					((SmartLight)getSmartObjectList().get(i)).onCome();
				else
					((SmartLight)getSmartObjectList().get(i)).onLeave();
			}
		}
	}
	
	public void controlMotion(boolean hasMotion, boolean isDay) {
		System.out.println("-------------------------------------------------\n"
				+ "Motion Control : HasMotion, isDay" + "\n-------------------------------------------------");
		for (int i = 0; i < getSmartObjectList().size(); i++) { //This loop checks if the ith index of getSmartObjectList is an instance of MotionControl or not, then invokes the given methods of objects.
			if (getSmartObjectList().get(i) instanceof MotionControl) {
				((SmartCamera)getSmartObjectList().get(i)).controlMotion(hasMotion, isDay);
			}
		}
	}
	
	public void controlProgrammable() {
		System.out.println("-------------------------------------------------\n"
				+ "Programmable : runProgram" + "\n-------------------------------------------------");
		for (int i = 0; i < getSmartObjectList().size(); i++) { //This loop checks if the ith index of getSmartObjectList is an instance of Programmable or not, then casts the object to its actual type, at the end invokes the given methods of objects.
			if (getSmartObjectList().get(i) instanceof Programmable) {
				if (getSmartObjectList().get(i) instanceof SmartLight)
					((SmartLight)getSmartObjectList().get(i)).runProgram();
				else if (getSmartObjectList().get(i) instanceof SmartPlug)
					((SmartPlug)getSmartObjectList().get(i)).runProgram();

			}
		}
	}
	
	public void controlTimer(int seconds) {
		System.out.println("-------------------------------------------------\n"
				+ "Programmable : Timer = " + seconds + "seconds" + "\n-------------------------------------------------");
		for (int i = 0; i < getSmartObjectList().size(); i++) { //This loop checks if the ith index of getSmartObjectList is an instance of SmartLight or SmartPlug, then casts the object to its actual type, at the end invokes the setTimer methods of objects with the given seconds value.
			if (getSmartObjectList().get(i) instanceof Programmable) { 
				if (getSmartObjectList().get(i) instanceof SmartLight) {
					if (seconds != 0) ((SmartLight)getSmartObjectList().get(i)).setTimer(seconds);
					else ((SmartLight)getSmartObjectList().get(i)).cancelTimer();
				}
				else if (getSmartObjectList().get(i) instanceof SmartPlug)
					if (seconds != 0) ((SmartPlug)getSmartObjectList().get(i)).setTimer(seconds);
					else ((SmartPlug)getSmartObjectList().get(i)).cancelTimer();
				
			}
		}
	}
	
	public void controlTimerRandomly () {
		System.out.println("-------------------------------------------------\n"
				+ "Programmable : Timer = 5 or 10 seconds randomly" + "\n-------------------------------------------------");
		
		for (int i = 0; i < getSmartObjectList().size(); i++) { //This loop checks if the ith index of getSmartObjectList is an instance of SmartLight or SmartPlug, then casts the object to its actual type, at the end invokes the setTimer methods of objects with the random second values.
			if (getSmartObjectList().get(i) instanceof Programmable) {
				int random = (int)(Math.random() * 3);
				if (getSmartObjectList().get(i) instanceof SmartLight) {
					if (random == 2) ((SmartLight)getSmartObjectList().get(i)).setTimer(10);
					else if (random == 1) ((SmartLight)getSmartObjectList().get(i)).setTimer(5);
					else ((SmartLight)getSmartObjectList().get(i)).cancelTimer();
				}
				else if (getSmartObjectList().get(i) instanceof SmartPlug)
					if (random == 2) ((SmartPlug)getSmartObjectList().get(i)).setTimer(10);
					else if (random == 1) ((SmartPlug)getSmartObjectList().get(i)).setTimer(5);
					else ((SmartPlug)getSmartObjectList().get(i)).cancelTimer();
			}
		}
	}
	
	public void sortCameras() { //This method sorts SmartCamera objects with respect to their batterylife's.
		
		System.out.println("-------------------------------------------------\n"
				+ "Sort Smart Cameras" + "\n-------------------------------------------------");
		
		
		ArrayList<SmartCamera> cameras = new ArrayList<SmartCamera>();
		for (int i = 0; i < getSmartObjectList().size(); i++)
			if (getSmartObjectList().get(i) instanceof SmartCamera)
				cameras.add(((SmartCamera)getSmartObjectList().get(i)));
		
		
		SmartCamera[] cmrs = cameras.toArray(new SmartCamera[cameras.size()]);
		Arrays.sort(cmrs);
		
		for (int i = 0; i < cmrs.length; i++)
			System.out.println(cmrs[i].toString());
		
	}

	public ArrayList<SmartObject> getSmartObjectList() {
		return smartObjectList;
	}

	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjectList = smartObjectList;
	}
	
	

}
