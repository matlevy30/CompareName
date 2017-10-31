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
		return values[16].toUpperCase().trim();
	}

	public String Hop1() {
		String value = CabPanel(values[6].toUpperCase().trim());
		return value;
	}

	public String Hop2() {
		String value = CabPanel(values[7].toUpperCase().trim());
		return value;
	}

	public String Hop3() {
		String value = CabPanel(values[8].toUpperCase().trim());
		return value;
	}

	public String Hop4() {
		String value = CabPanel(values[9].toUpperCase().trim());
		return value;
	}

	public String Hop5() {
		String value = CabPanel(values[10].toUpperCase().trim());
		return value;
	}

	public String Hop6() {
		String value = CabPanel(values[11].toUpperCase().trim());
		return value;
	}

	public String Hop7() {
		String value = CabPanel(values[12].toUpperCase().trim());
		return value;
	}

	public String Hop8() {
		String value = CabPanel(values[13].toUpperCase().trim());
		return value;
	}

	private String CabPanel(String panel) {
		String v = "";
		if (panel.contains("ER")) {
			StringBuilder sb = new StringBuilder();
			sb.append(panel.substring(0,2));
			sb.append(panel.charAt(3));
			sb.append(panel.substring(4,11));
			return sb.toString();	
		} else if (panel.contains(".") && panel.length() >= 9) {
			v = panel.substring(0, 9);
		} else {
			v = panel;
		}
		return v;
	}

	public String Cabinate(String panel) {
		// System.out.print(panel);
		if (panel.contains(".")) {
			return panel.substring(0, 6);
		}

		return "No CAB";
	}

	public String Pod(String value) {
		return Character.toString(value.charAt(0));
	}

	public String Row(String value) {

		if (value.length() >= 6) {
			return value.substring(3, 6);
		}
		return value;
	}

	// Method not used
	public String cabinateName() {
		return "Nothing here";
	}
}
