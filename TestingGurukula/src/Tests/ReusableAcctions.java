package Tests;

import java.io.File;
import java.io.FileInputStream;
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
        XSSFSheet sheet = Tests.Constants.workbook.createSheet("Gurukula");
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
        Tests.Constants.workbook.write(outputStream);
        Tests.Constants.workbook.close();
	}
	
	public void WriteResults(String Location, int Row, int Column, String Result) throws IOException {
		FileInputStream input= new FileInputStream(new File(Location));                  
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheetAt(0); 
		//select cell were to write results
		Cell cell = null;
		cell = sheet.getRow(Row).getCell(Column);
		cell.setCellValue(Result);
        
        //write results and close file
        input.close();
        FileOutputStream output = new FileOutputStream(new File(Location));
        workbook.write(output);
        output.close();
	}
	
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
	
	public void CreateBranch(String Name, String Code, int Row, int Column, int Single) throws InterruptedException, IOException {
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);
		
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Name);
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(Code);
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
		if (Single == 1) {
			String BranchName = Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
			String BranchCode = Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
			if (BranchName.equalsIgnoreCase(Name) && BranchCode.equalsIgnoreCase(Code)) {
				this.WriteResults(Tests.Constants.ExcelLocation, Row, Column, "Passed");
			}
			else {
				this.WriteResults(Tests.Constants.ExcelLocation, Row, Column, "Failed");
			}
		}
	}
	
	public void CreateStaff(String Name, int Row, int Column, int Single) throws InterruptedException, IOException {
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1500);
		
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Name);
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")).click();
		Thread.sleep(500);
		switch (Single) {
			case 1: Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[2]")).click();
			break;
			case 2: Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[2]")).click();
			break;
			case 3: Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[3]")).click();
			break;
			case 4: Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[2]")).click();
			break;
			case 5: Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[5]")).click();
			break;
		}					
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
				
		if (Single == 1) {
			String StaffName = Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
			String BranchName = Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
			if (StaffName.equalsIgnoreCase(Name) && BranchName.equalsIgnoreCase(Tests.Constants.Branch)) {
				this.WriteResults(Tests.Constants.ExcelLocation, Row, Column, "Passed");
			}
			else {
				this.WriteResults(Tests.Constants.ExcelLocation, Row, Column, "Failed");
			}
		}
	}
	
}
