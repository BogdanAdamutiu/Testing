package Tests;

import java.io.IOException;

import org.openqa.selenium.By;

public class AccountInformation {

	ReusableAcctions Action = new ReusableAcctions();
	String Status = "";
	String SecondStatus = "";
	String Email = "";
	String Language = "";
	
	public void OpenAccountInformation() throws InterruptedException {
		Action.OpenBrowser();
		Action.Login(Tests.Constants.User, Tests.Constants.Password);
		
		Action.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/a/span/span[2]")).click();
		Action.Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(500);
	}
	
	public void AccountInformationView() throws InterruptedException, IOException {	
		//check that page account information is opened
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/h2")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("User settings for [admin]")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,1,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,1,"Failed");
		}
		
		//check that user account information is correct
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).getAttribute("value");
		SecondStatus = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).getAttribute("value");
		Email = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).getAttribute("value");
		Language = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[4]/select")).getAttribute("innerText");
		if (Status.equalsIgnoreCase(Tests.Constants.FirstName) && 
			SecondStatus.equalsIgnoreCase(Tests.Constants.LastName) &&
			Email.equalsIgnoreCase(Tests.Constants.InitialEmail) &&
			Language.equalsIgnoreCase("English")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,2,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,2,"Failed");
		}		
	}
	
	//change all account information and save changes
	public void ChangeAccountInformation() throws InterruptedException, IOException {
		//change all account information and same changes
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.NewUser);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.NewUser);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Email);
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[3]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("An error has occurred!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,3,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,3,"Failed");
		}	
		
		//change back all account information and save
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.FirstName);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.LastName);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.InitialEmail);
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Settings saved!")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,4,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,4,"Failed");
		}
	}
	
	//check allowed data format for First Name
	public void ChangeAccountFirstName() throws InterruptedException, IOException {
		//check that field first name is mandatory
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your first name is required.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,5,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,5,"Failed");
		}
		
		//check if special characters can be added as first name
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Special);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,6,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,6,"Failed");
		}
		
		//check if a short first name is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(200);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Short);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,7,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,7,"Failed");
		}
		
		//check if a long first name is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(200);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Long);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,8,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,8,"Failed");
		}
		
		//check if a first name with upper case characters is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(200);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Upper);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,9,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,9,"Failed");
		}
		
		//check if first name can be made out of numbers and letters
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(200);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(Tests.Constants.Combined);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,10,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,10,"Failed");
		}		
	}
	
	//check allowed data format for Last Name
	public void ChangeAccountLastName() throws InterruptedException, IOException {
		//check that field last name is mandatory
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your last name is required.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,11,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,11,"Failed");
		}	
		
		//check if special characters can be added as last name
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Special);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,12,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,12,"Failed");
		}
		
		//check if a short last name is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(200);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Short);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,13,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,13,"Failed");
		}
		
		//check if a long last name is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(200);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Long);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,14,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,14,"Failed");
		}
		
		//check if a last name with upper case characters is allowed
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(200);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Upper);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,15,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,15,"Failed");
		}
		
		//check if last name can be made out of numbers and letters
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(200);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(Tests.Constants.Combined);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,16,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,16,"Failed");
		}
	}
	
	//check allowed data format for Email address
	public void ChangeAccountEmail() throws InterruptedException, IOException {
		//check that field email is mandatory
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[1]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your e-mail is required.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,17,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,17,"Failed");
		}
		
		//check if email without @xxxx.com can be added
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Incomplete);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your e-mail is invalid.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,18,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,18,"Failed");
		}
		Thread.sleep(500);
		
		//check if a short text can be added as an email
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Short);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[2]")).getAttribute("innerText");
		SecondStatus = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[3]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your e-mail is invalid.") && SecondStatus.equalsIgnoreCase("Your e-mail is required to be at least 5 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,19,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,19,"Failed");
		}
		Thread.sleep(500);
		
		//check if a mistake in a correct email is detected
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(Tests.Constants.Mistake);
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("Your e-mail is invalid.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,20,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,20,"Failed");
		}
		Thread.sleep(500);		
	}
	
	//check available languages
	public void ChangeAccountLanguage() throws IOException, InterruptedException {
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[4]/select")).getAttribute("length");
		if (Status.contentEquals("1")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,21,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,4,21,"Failed");
		}
		
		Action.Logout(4,22);
		Action.Close();
	}
}
