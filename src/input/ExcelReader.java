package input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements DocumentReader {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private String path;
	
	public ExcelReader(String path){
		this.path = path;
	}

	public List<String> read(){
		List<String> myList = new ArrayList<String>();
		try {
			// create a XSSF workbook object for the xlsx file
			workbook = new XSSFWorkbook(new FileInputStream(path));
			// get the first sheet
			sheet = workbook.getSheetAt(0);
			
			// iterate on rows
			Iterator<Row> rowIt = sheet.iterator();
			
			while(rowIt.hasNext()) {
				Row row = rowIt.next();
				
				// iterate cell on current row
				Iterator<Cell> cellIt = row.cellIterator();
				String rowStr = "";
				
				while(cellIt.hasNext()) {
					Cell cell = cellIt.next();
					if (!cell.toString().equals(""))
						rowStr += cell.toString() + " ; ";
					else
						rowStr += "    ; ";
				}
				myList.add(rowStr);
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return myList;
	}
	
}
