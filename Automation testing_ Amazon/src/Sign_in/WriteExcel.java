package Sign_in;

//import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	
	public void export(List<HashMap<String, Object>> ResultExport, String excelFilePath) {
		List<String> headers = Arrays.asList("IDtc", "username", "password", "message", "result");
	    int currentRowNumber = 0;
	    try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet spreadsheet = workbook.createSheet(" Test Report "); 
			Row header = spreadsheet.createRow(currentRowNumber);
			for (int i = 0; i < headers.size(); i++) {
			    Cell headerCell = header.createCell(i);
			    headerCell.setCellValue(headers.get(i));
			}
			for (int i = 0; i < ResultExport.size(); i++) {
			    HashMap<String, Object> row = ResultExport.get(i);

			    currentRowNumber++;
			    Row sheetRow = spreadsheet.createRow(currentRowNumber);

			    for (int j = 0; j < headers.size(); j++) {
			        Cell cell = sheetRow.createCell(j);

			        String currentColumnName = headers.get(j);
			        if (row.containsKey(currentColumnName)) {
			            cell.setCellValue(row.get(currentColumnName).toString());
			        }
			    }
			    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			        workbook.write(outputStream);
			    }
}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
