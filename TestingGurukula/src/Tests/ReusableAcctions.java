package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
public class ReusableAcctions {
	
	FirefoxDriver Mozila = new FirefoxDriver();	
	String Status = "";
	String ID = "";
	String Branch = "";	
	String Code = "";
	String Staff = "";
	String Results = "0";
	int Searches = 1;
	int NrOfResults = 0;
	int FoundResult = 0;
		
	public void ResultsFile(String Location) throws IOException {
        XSSFSheet sheet = Tests.Constants.workbook.createSheet("Gurukula");
        //create table header and write test cases
        for (int r = 0; r < 11; r++) {
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
        workbook.close();
	}
	
	public void OpenBrowser() {
		Mozila.manage().window().maximize();;
		Mozila.navigate().to("http://192.168.178.227:8080/");
		Mozila.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	public void Login(String User, String Password) throws InterruptedException {
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).click();		
		Thread.sleep(1000);		
		Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(User);
		Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Password);
		Mozila.findElement(By.xpath("//*[@id=\"rememberMe\"]")).click();
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(2000);
	}
	
	public void NavigateToBranch() throws InterruptedException {
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(1000);
	}
	
	public void NavigateToStaff() throws InterruptedException {
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(1000);
	}
	
	public void CreateBranch(String Name, String Code, int Row, int Column, int Single) throws InterruptedException, IOException {
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);
		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(Code);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
		if (Single == 1) {
			String BranchName = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
			String BranchCode = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
			if (BranchName.equalsIgnoreCase(Name) && BranchCode.equalsIgnoreCase(Code)) {
				this.WriteResults(Tests.Constants.ExcelLocation, Row, Column, "Passed");
			}
			else {
				this.WriteResults(Tests.Constants.ExcelLocation, Row, Column, "Failed");
			}
		}
	}
	
	public void CreateStaff(String Name, int Row, int Column, int Single) throws InterruptedException, IOException {
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1500);
		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")).click();
		Thread.sleep(500);
		switch (Single) {
			case 1: Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[2]")).click();
			break;
			case 2: Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[2]")).click();
			break;
			case 3: Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[3]")).click();
			break;
			case 4: Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[2]")).click();
			break;
			case 5: Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[5]")).click();
			break;
		}					
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
				
		if (Single == 1) {
			String StaffName = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
			String BranchName = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
			if (StaffName.equalsIgnoreCase(Name) && BranchName.equalsIgnoreCase(Tests.Constants.Branch)) {
				this.WriteResults(Tests.Constants.ExcelLocation, Row, Column, "Passed");
			}
			else {
				this.WriteResults(Tests.Constants.ExcelLocation, Row, Column, "Failed");
			}
		}
	}
	
	public void ViewBranch() throws InterruptedException, IOException {		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[1]")).click();
		Thread.sleep(1000);
		
		Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/span")).getAttribute("innerText");
		Branch = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[1]/td[2]/input")).getAttribute("defaultValue");
		Code = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[2]/td[2]/input")).getAttribute("defaultValue");
		if (Status.equalsIgnoreCase("Branch") && 
			(Branch.equalsIgnoreCase(Tests.Constants.Branch)) && 
			Code.equalsIgnoreCase(Tests.Constants.Code)) {
			this.WriteResults(Tests.Constants.ExcelLocation,7,1,"Passed");
		}
		else {
			this.WriteResults(Tests.Constants.ExcelLocation,7,1,"Failed");
		}
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/button")).click();
	}
	
	public void ViewStaff() throws InterruptedException, IOException {		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[1]")).click();
		Thread.sleep(1000);
		
		Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/span")).getAttribute("innerText");
		Staff = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[1]/td[2]/input")).getAttribute("defaultValue");
		Branch = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[2]/td[2]/input")).getAttribute("defaultValue");
		if (Status.equalsIgnoreCase("Staff") && 
			Staff.equalsIgnoreCase(Tests.Constants.Staff) && 
			Branch.equalsIgnoreCase(Tests.Constants.Branch)) {
			this.WriteResults(Tests.Constants.ExcelLocation,7,2,"Passed");
		}
		else {
			this.WriteResults(Tests.Constants.ExcelLocation,7,2,"Failed");
		}
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/button")).click();
	}
	
	public void EditBranch(String BranchName, String BranchCode, int RowResults, int ColumnResults) throws InterruptedException, IOException {		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[2]")).click();
		Thread.sleep(1000);
		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(BranchName);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(BranchCode);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		Branch = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		Code = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (Branch.equalsIgnoreCase(Tests.Constants.NewBranch) && Code.equalsIgnoreCase(Tests.Constants.NewCode)) {
			this.WriteResults(Tests.Constants.ExcelLocation,RowResults,ColumnResults,"Passed");
		}
		else {
			this.WriteResults(Tests.Constants.ExcelLocation,RowResults,ColumnResults,"Failed");
		}
	}
	
	public void EditStaff(String StaffName, int RowResult, int ColumnResult) throws InterruptedException, IOException {	
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(StaffName);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		Staff = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		Branch = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (Staff.equalsIgnoreCase(StaffName) && Branch.equalsIgnoreCase(Tests.Constants.NewBranch)) {
			WriteResults(Tests.Constants.ExcelLocation,RowResult,ColumnResult,"Passed");
		}
		else {
			WriteResults(Tests.Constants.ExcelLocation,RowResult,ColumnResult,"Failed");
		}
	}
	
	public void QueryBranch(String SearchCriteria) throws InterruptedException, IOException {
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(SearchCriteria);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			this.WriteResults(Tests.Constants.ExcelLocation,10,Searches,"No results");
			Searches ++;
		}
		else if (NrOfResults >= 1) {
			for (int i = 1; i <= NrOfResults; i++) {
				ID = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr[" + i + "]/td[1]/a")).getAttribute("innerText");
				Branch = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr[" + i + "]/td[2]")).getAttribute("innerText");
				Code = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr[" + i + "]/td[3]")).getAttribute("innerText");
				if (ID.equalsIgnoreCase(SearchCriteria) || Branch.equalsIgnoreCase(SearchCriteria) || Code.equalsIgnoreCase(SearchCriteria)) {
					this.WriteResults(Tests.Constants.ExcelLocation,10,Searches,"Found a match");
					Searches ++;
					FoundResult ++;
				}						
			}
			if (FoundResult == 0) {
				this.WriteResults(Tests.Constants.ExcelLocation,10,Searches,"No results");
				Searches ++;				
			}
		}		
	}
		
	public void QueryStaff(String SearchCriteria) throws InterruptedException, IOException {
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(SearchCriteria);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			this.WriteResults(Tests.Constants.ExcelLocation,10,Searches,"No results");
			Searches ++;
		}
		else if (NrOfResults >= 1) {
			for (int i = 1; i <= NrOfResults; i++) {
				ID = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr[" + i + "]/td[1]/a")).getAttribute("innerText");
				Staff = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr[" + i + "]/td[2]")).getAttribute("innerText");
				if (ID.equalsIgnoreCase(SearchCriteria) || Staff.equalsIgnoreCase(SearchCriteria)) {
					this.WriteResults(Tests.Constants.ExcelLocation,10,Searches,"Found a match");
					Searches ++;
					FoundResult++;
				}						
			}
			if (FoundResult == 0) {
				this.WriteResults(Tests.Constants.ExcelLocation,10,Searches,"No results");
				Searches ++;				
			}
		}	
	}
	
	public void DeteleBranch(int StaffDeleted) throws InterruptedException, IOException {		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).click();
		Thread.sleep(500);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		if (StaffDeleted == 0) {
			Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).getAttribute("disabled");
			if (Status != null) {
				this.WriteResults(Tests.Constants.ExcelLocation,9,1,"Failed");
			}
			else {
				this.WriteResults(Tests.Constants.ExcelLocation,9,1,"Passed");
				this.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[1]")).click();
				Thread.sleep(1000);
			}
		}
		else if (StaffDeleted == 1) {
			Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).getAttribute("disabled");
			if (Status != null) {
				this.WriteResults(Tests.Constants.ExcelLocation,9,3,"Passed");
			}
			else {
				this.WriteResults(Tests.Constants.ExcelLocation,9,3,"Failed");
			}
		}
	}
	
	public void DeleteStaff() throws InterruptedException, IOException {		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).click();
		Thread.sleep(500);		
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(500);
		Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).getAttribute("disabled");
		if (Status != null) {
			this.WriteResults(Tests.Constants.ExcelLocation,9,2,"Passed");
		}
		else {
			this.WriteResults(Tests.Constants.ExcelLocation,9,2,"Failed");
		}
	}
	
	public void Logout(int RowLogout, int ColumnLogout) throws InterruptedException, IOException {
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/a/span/span[2]")).click();
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/ul/li[4]/a/span[2]")).click();
		Thread.sleep(1500);
		String Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/h1")).getAttribute("innerText");												
		if (Status == "Welcome to Gurukula!") {
			WriteResults(Tests.Constants.ExcelLocation, RowLogout, ColumnLogout, "Passed");
		}
		else {
			WriteResults(Tests.Constants.ExcelLocation, RowLogout, ColumnLogout, "Failed");
		}
	}
	
	public void Close() throws InterruptedException {
		Mozila.close();
		Thread.sleep(5000);
	}
}