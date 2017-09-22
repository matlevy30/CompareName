package main.java.jar;

public class HardwareSheet extends Sheet {

	public HardwareSheet(String[] values) {
		super(values);
	}
	public String HostName1() {
		// TODO Auto-generated method stub
		return null;
	}
	public String Cabinate1() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String HostName2() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String Cabinate2() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String Hop1() {
		// TODO Auto-generated method stub
		return null;
	}
	public String Hop2() {
		// TODO Auto-generated method stub
		return null;
	}
	public String Hop3() {
		// TODO Auto-generated method stub
		return null;
	}
	public String Hop4() {
		// TODO Auto-generated method stub
		return null;
	}
	public String Hop5() {
		// TODO Auto-generated method stub
		return null;
	}
	public String Hop6() {
		// TODO Auto-generated method stub
		return null;
	}
	public String Hop7() {
		// TODO Auto-generated method stub
		return null;
	}
	public String Hop8() {
		// TODO Auto-generated method stub
		return null;
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
