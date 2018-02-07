package Tests;

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
        Row row = sheet.createRow(0);
        for (int c = 0; c < 6; c++) {
        	Cell cell = row.createCell(c);
        	if (c == 0) {
        		cell.setCellValue("Test Name");
        	}
        	else {
        		cell.setCellValue("Test step");
        	}
        }        
        FileOutputStream outputStream = new FileOutputStream(Location);
        workbook.write(outputStream);
        workbook.close();
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
