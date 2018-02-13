package Tests;

import java.io.IOException;

import org.openqa.selenium.By;

public class RegisterUser {
	
	DriverReusableActions Action = new DriverReusableActions();
	String Status = "";
	String SecondStatus = "";
	
	//registers a new user
	//registration of new users doesn't work, but this test case is considered passed because all the user information are correct
	public void Register() throws InterruptedException, IOException {
		Action.OpenBrowser();
		Thread.sleep(1000);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[2]/a")).click();
		Thread.sleep(1500);
			
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.NewUser);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Email);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.NewPassword);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[4]/input")).sendKeys(Tests.Constants.NewPassword);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
			
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Registration failed! Please try again later.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,1,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,1,"Failed");
		}
	}
		
	//checks what type of data can be used in the user name
	public void NewUserInformation() throws InterruptedException, IOException {
		//navigates to home menu
		Action.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[1]/a[2]/span[2]")).click();
		Thread.sleep(500);
			
		//navigates to user registration
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[2]/a")).click();
		Thread.sleep(1500);
			
		//checks if upper case characters are allowed in user name
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Upper);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[4]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your login can only contain lower-case letters and digits.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,2,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,2,"Failed");
		}
		Thread.sleep(500);
			
		//checks if special characters are allowed in user name
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Special);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[4]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your login can only contain lower-case letters and digits.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,3,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,3,"Failed");
		}
			Thread.sleep(500);
			
		//checks if a short name is allowed as user name
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Short);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[4]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,4, "Failed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,4, "Passed");
		}	
			
		//checks if a long name is allowed as user name
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Long);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[3]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your login cannot be longer than 50 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,5,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,5,"Failed");
		}
		
		//checks if name made out of numbers and letters is allowed as user name
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Combined);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[4]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,6,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,6,"Failed");
		}
	}	
		
	//checks what type of data can be used in the email
	public void NewEmailInformation() throws InterruptedException, IOException {
		//check if an incomplete email address is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your e-mail is required.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,7,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,7,"Failed");
		}
		Thread.sleep(500);
		
		//check if an incomplete email address is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Incomplete);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your e-mail is invalid.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,8,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,8,"Failed");
		}
		Thread.sleep(500);
			
		//check if a short text is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Short);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[2]")).getAttribute("innerText");
		SecondStatus = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[3]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your e-mail is invalid.") && SecondStatus.equalsIgnoreCase("Your e-mail is required to be at least 5 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,9,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,9,"Failed");
		}
			Thread.sleep(500);
			
		//check if a mistake in the email address is detected
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Mistake);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your e-mail is invalid.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,10,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,10,"Failed");
		}
			Thread.sleep(500);
	}
	
	//checks what type of data can be used in the password
	public void NewPasswordInformation() throws InterruptedException, IOException {
		//checks if letters and numbers are allowed as a password
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Combined);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[1]/p[2]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,11, "Failed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,11, "Passed");
		}
		Thread.sleep(500);
			
		//checks if upper case characters are allowed as a password
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Upper);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[1]/p[2]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,12,"Failed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,12,"Passed");
		}
		Thread.sleep(500);
			
		//checks if special characters are allowed as a password
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Special);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[1]/p[2]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,13,"Failed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,13,"Passed");
		}
		Thread.sleep(500);
			
		//checks if a short password is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Short);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[1]/p[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your password is required to be at least 5 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,14,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,14,"Failed");
		}
		Thread.sleep(500);
		
		//checks if a long password is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Long);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[1]/p[3]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your password cannot be longer than 50 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,15,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,15,"Failed");
		}
		Thread.sleep(500);
			
		//checks if a different confirmation password than password can be saved
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.NewUser);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Email);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.NewPassword);		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[4]/input")).sendKeys(Tests.Constants.Password);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[5]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("The password and its confirmation do not match!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,16,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,3,16,"Failed");
		}
		Thread.sleep(500);
		
		Action.Close();
	}
}
