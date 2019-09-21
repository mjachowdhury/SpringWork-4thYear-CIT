package ie.mohammed.com;

public class PowerImplementation implements Power {
	public String powerName;
	public String powerDescription;
	
	public PowerImplementation() {}
 	public PowerImplementation(String powerName, String powerDescription) {
		super();
		this.powerName = powerName;
		this.powerDescription = powerDescription;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getPowerDescription() {
		return powerDescription;
	}

	public void setPowerDescription(String powerDescription) {
		this.powerDescription = powerDescription;
	}

	@Override
	public String toString() {
		return "\n Power :" + powerName + "------>" + powerDescription;
	}
	
	
	
	

}
