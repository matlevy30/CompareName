package main.java.jar;

public class UpdateSheet extends Sheet {

	public UpdateSheet(String[] values) {
		super(values);
	}

	public String HostName() {
		return values[0].toUpperCase().trim();
	}

	public String cabinateName() {
		return values[1].toUpperCase().trim();
	}

	public String getPod() {
		return values[2].toUpperCase().trim();
	}

	public String getRow() {
		return values[3].toUpperCase().trim();
	}
}
