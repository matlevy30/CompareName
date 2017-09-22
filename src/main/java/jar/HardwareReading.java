package main.java.jar;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HardwareReading extends Reading {
	private XSSFSheet reader;

	public HardwareReading() throws IOException {
		String fileName = "src/Hardware.xlsx";
		XSSFWorkbook myWorkBook = new XSSFWorkbook(new FileInputStream(fileName));
		reader = myWorkBook.getSheetAt(0);
		myWorkBook.close();
		lines = new ArrayList<Sheet>();
		// Header Fields
		header = new String[12];
	}

	public void readLines() throws IOException {
		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = reader.iterator();
		// Blank Lines
		rowIterator.next();
		rowIterator.next();
		// Header
		createHeader(rowIterator.next());
		while (rowIterator.hasNext()) {
			// Grabbing each row
			Row row = rowIterator.next();
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			String[] line = cellIterator(cellIterator);
			lines.add(new HardwareSheet(line));
		}
	}

	// Filtering Blank and N/A Serial Number
	@SuppressWarnings("unused")
	private boolean blankSerial(String[] line) {
		if (line[3].equals("") || line[3].equals("N/A")) {
			return false;
		}
		return true;
	}

	// Filtering Blank and N/A Asset Tag
	@SuppressWarnings("unused")
	private boolean blankTag(String[] line) {
		String tag = line[4].toUpperCase();
		if (tag.equals("") || tag.equals("N/A") || tag.contains("CHILD")) {
			return false;
		}

		return true;
	}

	// Filtering Type
	@SuppressWarnings("unused")
	private boolean filterType(String[] line) {
		if (line[10].equals("Server") || line[10].equals("Cabinet") || line[10].equals("Chassis")
				|| line[10].equals("Peripheral") || line[10].equals("KVMSwitch") || line[10].equals("Network")
				|| line[10].equals("Powerstrip")) {
			return true;
		}
		return false;
	}

	// Filtering Locations
	protected boolean filterLocation(String[] line) {
		// OCC Locations Rooms
		if (line[7].contains("Highlands Ranch")) {
			if (line[7].contains("Data Hall") || line[7].contains("Floor")) {
				return true;
			}
		}
		// OCE Locations Rooms
		else if (line[7].contains("Ashburn")) {
			if (line[7].contains("POD ")) {
				return true;
			}
		} else if (line[7].contains("Singapore")) {
			return true;
		}
		return false;
	}

	// Getting the header of the file
	private void createHeader(Row row) {
		// For each row, iterate through each columns
		Iterator<Cell> cellIterator = row.cellIterator();
		header = cellIterator(cellIterator);
	}

	private String[] cellIterator(Iterator<Cell> cellIterator) {
		String[] values = new String[12];
		int i = 0;
		while (cellIterator.hasNext()) {
			// Read each column cell
			Cell cell = cellIterator.next();

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				values[i] = Double.toString(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				values[i] = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				values[i] = " ";
				break;
			default:
				values[i] = " ";
			}
			++i;
		}
		return values;
	}

}
