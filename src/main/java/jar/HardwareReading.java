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
		String fileName = "src/OCE Hardware-Network Master Log 2017.xlsx";
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

	@Override
	protected boolean filterLocation(String[] line) {
		return false;
	}

}
