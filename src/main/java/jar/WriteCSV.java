package main.java.jar;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteCSV {

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
   
	private String[] header = {"Name", "Cabinate","Pod","Row"};
	private ArrayList<UpdateSheet> list;
	private String fileName;

	public WriteCSV(String[] header, ArrayList<UpdateSheet> list, String fileName) {
		
		this.list = list;
		this.fileName = fileName;
		
	}

	public void wirte() {

		FileWriter file = null;
		try {
			file = new FileWriter(fileName);
			//Header
			int z = 0;
			while (z != header.length - 1) {
				file.append(header[z++]);
				file.append(COMMA_DELIMITER);
			}
			file.append(header[z]);
			file.append(NEW_LINE_SEPARATOR);
			
			
			//Rest of Content
			for (int i = 0; i != list.size(); ++i) {
				int j = 0;
				String[] l = list.get(i).getLine();
				while (j != l.length - 1) {
					file.append(l[j++]);
					file.append(COMMA_DELIMITER);
				}
				file.append(l[j]);
				file.append(NEW_LINE_SEPARATOR);
				j = 0;
			}

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}

	}
}

