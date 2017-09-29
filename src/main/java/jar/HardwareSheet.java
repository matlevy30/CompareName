package main.java.jar;

public class HardwareSheet extends Sheet {

	public HardwareSheet(String[] values) {
		super(values);
	}
	public String HostName1() {
		return values[4].toUpperCase().trim();
	}
	public String Cabinate1() {
		return values[3].toUpperCase().trim();
	}
	
	public String HostName2() {
		return values[14].toUpperCase().trim();
	}
	
	public String Cabinate2() {
		return values[15].toUpperCase().trim();
	}
	
	public String Hop1() {
		return values[6].toUpperCase().trim();
	}
	public String Hop2() {
		return values[7].toUpperCase().trim();
	}
	public String Hop3() {
		return values[8].toUpperCase().trim();
	}
	public String Hop4() {
		return values[9].toUpperCase().trim();
	}
	public String Hop5() {
		return values[10].toUpperCase().trim();
	}
	public String Hop6() {
		return values[11].toUpperCase().trim();
	}
	public String Hop7() {
		return values[12].toUpperCase().trim();
	}
	public String Hop8() {
		return values[13].toUpperCase().trim();
	}
	public String CabPanel(String panel) {
		return panel.substring(0,5);
	}
	public  String Pod(String value) {
		return Character.toString(value.charAt(0));
	}
	public String Row(String value) { 
		return value.substring(3, 5);
	}

}
