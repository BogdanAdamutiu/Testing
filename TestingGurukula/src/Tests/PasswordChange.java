package Tests;

import java.io.IOException;

import org.openqa.selenium.By;

public class PasswordChange {

	DriverReusableActions Action = new DriverReusableActions();
	String Status = "";
	
	public void OpenPasswordInformation() throws InterruptedException, IOException {
		Action.OpenBrowser();
		Thread.sleep(1000);
		Action.Login(Tests.Constants.User, Tests.Constants.Password);
		Action.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/a/span/span[2]")).click();
		Action.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/ul/li[2]/a/span[2]")).click();
		Thread.sleep(500);
			
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/h2")).getAttribute("textContent");
		if (Status.equalsIgnoreCase("Password for [admin]")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,1,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,1,"Failed");
		}	
	}
	
	//checks what data types are allowed to be set in a password
	public void PasswordChange() throws InterruptedException, IOException {
		//checks that password field is mandatory
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[1]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your password is required.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,2,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,2,"Failed");
		}
		Thread.sleep(500);
			
		//checks if password can be changed
		//application doesn't allow password to be change, but this test is considered passed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.NewPassword);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.NewPassword);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("An error has occurred!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,3,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,3,"Failed");
		}
		Thread.sleep(500);
			
		//checks if password can be change to initial password
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Password);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Password);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("An error has occurred!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,4,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,4,"Failed");
		}
		Thread.sleep(500);
			
		//checks if letters and numbers can be set as a password
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Combined);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[2]")).getAttribute("innerText");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,5,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,5,"Failed");
		}
		Thread.sleep(500);
			
		//checks if upper case characters are allowed in password
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Upper);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[2]")).getAttribute("innerText");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,6,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,6,"Failed");
		}
		Thread.sleep(500);
			
		//checks if special characters are allowed in password
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Special);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[2]")).getAttribute("innerText");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,7,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,7,"Failed");
		}
		Thread.sleep(500);
			
		//checks if a short password can be set
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Short);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your password is required to be at least 5 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,8,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,8,"Failed");
		}
		Thread.sleep(500);
		
		//checks if a long password can be set
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Long);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[3]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your password cannot be longer than 50 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,9,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,9,"Failed");
		}
		Thread.sleep(500);
			
		//checks if a different confirmation password then the password can be saved
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.NewPassword);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Password);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[3]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("The password and its confirmation do not match!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,10,"Passed");
		}	
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,2,10,"Failed");
		}
		Thread.sleep(500);
		
		Action.Logout(2, 11);
		Action.Close();
	}
	
}
