package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
public class ReusableAcctions {
	
	public void ResultsFile(String Location) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Gurukula");
        //create table header and write test cases
        for (int r = 0; r < 12; r++) {
        	Row row = sheet.createRow(r);
            for (int c = 0; c < 6; c++) {
            	Cell cell = row.createCell(c);
            	switch (r) {
					case 0: if (c == 0) {
								cell.setCellValue("Test Name");
							}
							else if (c > 0) {
			            		cell.setCellValue("Test step");
			            	}
					break;
					case 1: if (c == 0) {
								cell.setCellValue("Login Information");
							}
					break;
					case 2:	if (c == 0) {
								cell.setCellValue("Password Change");
							}
					break;
					case 3: if (c == 0) {
								cell.setCellValue("New User Registration");
							}
					break;
					case 4: if (c == 0) {
								cell.setCellValue("Account Information");
							}
					break;
					case 5: if (c == 0) {
								cell.setCellValue("Data format for Branch and Staff");
							}
					break;
					case 6:	if (c == 0) {
								cell.setCellValue("Create");
							}
					break;
					case 7: if (c == 0) {
								cell.setCellValue("View");
							}
					break;
					case 8: if (c == 0) {
								cell.setCellValue("Edit");
							}
					break;
					case 9: if (c == 0) {
								cell.setCellValue("Delete");
							}
					break;
					case 10: if (c == 0) {
								cell.setCellValue("Query Branch and Staff");
							}					
					break;
					case 11: if (c == 4) {
								cell.setCellValue("Total");
							}
					break;
            	}
            } 
        }               
        FileOutputStream outputStream = new FileOutputStream(Location);
        workbook.write(outputStream);
        workbook.close();
	}
	
//	public void WriteResults(String Location, int Row, int Column) {
//		FileInputStream input= new FileInputStream(new File(Location));                  
//		XSSFWorkbook workbook = new XSSFWorkbook(input); 
//		XSSFSheet sheet = workbook.getSheetAt(0); 
//		//write results
//        Row row = sheet.createRow(Row);
//        for (int c = 0; c < 6; c++) {
//        	Cell cell = row.createCell(c);
//        	if (c == 0) {
//        		cell.setCellValue("Test Name");
//        	}
//        	else {
//        		cell.setCellValue("Test step");
//        	}
//        }        
//        FileOutputStream outputStream = new FileOutputStream(Location);
//        workbook.write(outputStream);
//        workbook.close();		
//		// declare a Cell object
//		Cell cell = null; 
//		// Access the second cell in second row to update the value
//		cell = worksheet.getRow(1).getCell(1);   
//		// Get current cell value value and overwrite the value
//		cell.setCellValue("OverRide existing value");
//		//Close the InputStream  
//		fsIP.close(); 
//		//Open FileOutputStream to write updates
//		FileOutputStream output_file =new FileOutputStream(new File("C:\\Excel.xls"));  
//		 //write changes
//		wb.write(output_file);
//		//close the stream
//		output_file.close();
//	}
	
	public void OpenBrowser() {
		Tests.Constants.Mozila.manage().window().maximize();;
		Tests.Constants.Mozila.navigate().to("http://192.168.178.227:8080/");
		Tests.Constants.Mozila.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	public void Login(String User, String Password) throws InterruptedException {
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).click();		
		Thread.sleep(1000);		
		Tests.Constants.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(User);
		Tests.Constants.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Password);
		Tests.Constants.Mozila.findElement(By.xpath("//*[@id=\"rememberMe\"]")).click();
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(2000);
	}
	
	public void NavigateToBranch() throws InterruptedException {
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(1000);
	}
	
	public void NavigateToStaff() throws InterruptedException {
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(1000);
	}
	
	public void CreateBranch (String Name, String Code) throws InterruptedException {
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);
		
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Name);
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(Code);
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
		String BranchName = Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		String BranchCode = Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (BranchName.equalsIgnoreCase(Name) && BranchCode.equalsIgnoreCase(Code)) {
			
		}
	}
	
	
}
