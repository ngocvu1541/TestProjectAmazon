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
	/*public void createHeaderRow(Sheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
     
        Row row = sheet.createRow(0);
        Cell cellTestcase = row.createCell(1);
        
        cellTestcase.setCellStyle(cellStyle);
        cellTestcase.setCellValue("TestCase");
        
        Cell cellUsername = row.createCell(2);
        cellUsername.setCellStyle(cellStyle);
        cellUsername.setCellValue("UserName");
     
        Cell cellPassword = row.createCell(3);
        cellPassword.setCellStyle(cellStyle);
        cellPassword.setCellValue("Password");
     
        Cell cellMessage = row.createCell(4);
        cellMessage.setCellStyle(cellStyle);
        cellMessage.setCellValue("Message");
        
        Cell cellResult = row.createCell(5);
        cellResult.setCellStyle(cellStyle);
        cellResult.setCellValue("Result");

    }
		
	public Workbook getWorkbook(String excelFilePath)throws IOException {
        Workbook workbook = null;
     
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
     
        return workbook;
    }

	private void writeHashMap (HashMap<String,Object> testResult, Row row) {
		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
	
			int colo=0; 
			for (HashMap.Entry<String, Object> element : testResult.entrySet()) { 
		        
				Cell col =row.createCell(colo++); 
                ((Row) col).createCell(0).setCellValue((String)element.getKey()); 
                ((Row) col).createCell(1).setCellValue((String)element.getValue()); 
	
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	*/
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
