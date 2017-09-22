package main.java.jar;


public abstract class Sheet {

	String[]values;
	
	public Sheet(String[] values) {
		this.values = values;
	}

	public  String assetTag() {
		throw new UnsupportedOperationException("Can't do this with Hardware sheet");
	}

	public  String serialNumber() {
		throw new UnsupportedOperationException("Can't do this with Hardware sheet");
	}

	public  String HostName() {
		throw new UnsupportedOperationException("Can't do this with Hardware sheet");
	}
	
	public String getNumber() {
		throw new UnsupportedOperationException("Can't do this with Hardware sheet");
	}

	public String[] getLine() {
		return values;
	}

	public void setSerial(String s) {
		throw new UnsupportedOperationException("Can't do this with Hardware sheet");
	}
	
	public void setAssetTag(String s) {
		throw new UnsupportedOperationException("Can't do this with Harwdware sheet");
	}

	// Printing line values, local methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != values.length; ++i) {
			sb.append(values[i] + ", ");
		}
		return sb.toString();
	}
}
