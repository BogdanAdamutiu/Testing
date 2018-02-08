package Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginInformation {
	
	ReusableAcctions Action = new ReusableAcctions();
	String Status = "";
	
	public void UserInformation() throws InterruptedException, IOException {
		Action.OpenBrowser();
		Thread.sleep(1000);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).click();
		Thread.sleep(1000);
		
		//checks if login work without user and password
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Authentication failed!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,1,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,1,"Failed");
		}
		
		//checks if login works only with user
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(Tests.Constants.User);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Authentication failed!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,2,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,2,"Failed");
		}
		
		//checks if login works only with password
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).clear();		
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Tests.Constants.Password);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Authentication failed!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,3,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,3,"Failed");
		}
		
		//checks if login works with wrong user and wrong password
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(Tests.Constants.NewUser);
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Tests.Constants.NewPassword);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Authentication failed!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,4,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,4,"Failed");
		}
		
		//checks if login works with good user and wrong password
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(Tests.Constants.User);
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Tests.Constants.NewPassword);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Authentication failed!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,5,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,5,"Failed");
		}
		
		//checks if login works with wrong user and good password
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(Tests.Constants.NewUser);
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Tests.Constants.Password);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Authentication failed!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,6,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,6,"Failed");
		}
	}
	
	public void RememberLogin() throws InterruptedException, IOException {
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		Action.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(Tests.Constants.User);
		Action.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Tests.Constants.Password);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(1000);
		
		Action.Mozila.quit();
		FirefoxDriver driver = new FirefoxDriver();
		driver.navigate().to("http://192.168.178.227:8080/");
		driver.manage().window().maximize();
		Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,7,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,1,7,"Failed");
		}
		driver.quit();
	}	
}