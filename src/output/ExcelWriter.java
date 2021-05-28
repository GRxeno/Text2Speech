package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter implements DocumentWriter {
	
	private String path;
	
	public ExcelWriter(String path){
		this.path = path;
	}

	public boolean write(List<String> myList){
		
		//String fileName = path.substring(path.lastIndexOf('/') + 1);
		
		if (myList.isEmpty()) {
			// TO-DO Create empty file
			return false;
		}
		
		FileOutputStream out = null;
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet 1");
		
		for (int i = 0; i < myList.size(); i++) {
			
			Row row = sheet.createRow(i+1);
			
			String[] arrOfStr = myList.get(i).split(";", -1);
			
			for (int j = 0; j < arrOfStr.length; j++) {
				
				Cell cell = row.createCell(j+1);
				
				try {
					
					out = new FileOutputStream(new File(path));
					cell.setCellValue(arrOfStr[j]);
					CellStyle cs = workbook.createCellStyle();  
		            cs.setWrapText(true);  
		            cell.setCellStyle(cs);  
		            //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));  
		            sheet.autoSizeColumn(j); 
		            workbook.write(out);
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
