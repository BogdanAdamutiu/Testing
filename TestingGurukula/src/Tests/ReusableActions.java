package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReusableActions {
	
	int TestCaseIndex = 0;
	int StepNameRow = 0;
	int StepResultRow = 0;
	int row = 0;
	int column = 0;
	
	/**
	   * This method is used to create an excel file containing the results of the test cases.
	   * The file created contains a header and the names of the test cases
	   * @param Location This is the location for were the excel file is created
	   * @exception IOException On input error
	*/
	public void ResultsFile(String Location) throws IOException {
      XSSFSheet sheet = Tests.Constants.workbook.createSheet("Gurukula");
      //create table header and write test cases
      for (row = 0; row < 99; row++) {
      	Row CreateRow = sheet.createRow(row);
        for (column = 0; column < 99; column++) {
          Cell CreateCell = CreateRow.createCell(column);
          if (row == 0 && column == 0) {
        	  CreateCell.setCellValue("Test Name");
		  }
          //check if the row number is a multiple of 2
          else if (row % 2 == 0 && column == 0 && TestCaseIndex < Tests.Constants.TestCases.length) {
        	  CreateCell.setCellValue(Tests.Constants.TestCases[TestCaseIndex]);
          	  TestCaseIndex ++;
          }
          else {
        	  CreateCell.setCellValue("");
          }
        }        
      }               
      FileOutputStream outputStream = new FileOutputStream(Location);
      Tests.Constants.workbook.write(outputStream);
      Tests.Constants.workbook.close();
	}
	
	/**
	   * This method is used to write in the results file the name of the method that was executed
	   * and the result of that method
	   * @param Location This is the location of the file in which to write the results
	   * @param Result This is the result of the method
	   * @param Method This is the name of the method from which we want to get the result
	   * @param TestCase This is the name of the test case in which the method is executed
	   * @exception IOException On input error
	*/
	public void WriteResults(String Location, String Result, String Method, String TestCase) throws IOException {
		FileInputStream input= new FileInputStream(new File(Location));                  
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheetAt(0);
		//get the row number of the test case
		for (row = 0; row < 99; row++) {
			Row WorkingRow = sheet.getRow(row);			
			Cell CurentCell = WorkingRow.getCell(column);
			if (CurentCell.getStringCellValue().equalsIgnoreCase(TestCase)) {
				StepNameRow = row - 1;
				StepResultRow = row;
			}
		}
		//get the next empty column of the row of the test case and write the results
		Row NameRow = sheet.getRow(StepNameRow);
		Row ResultsRow = sheet.getRow(StepResultRow);
		for (column = 1; column < 99; column++)	{
			Cell ResultsCell = ResultsRow.getCell(column);
			if (ResultsCell.getStringCellValue().equalsIgnoreCase("")) {
				NameRow.createCell(column).setCellValue(Method);
				ResultsRow.createCell(column).setCellValue(Result);
				column = 100;
			}
		}
		     
        //write results and close file
		input.close();
		FileOutputStream output = new FileOutputStream(new File(Location));
		workbook.write(output);
		output.close();
		workbook.close();
		column = 0;
	}
}