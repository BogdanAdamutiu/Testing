package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAccountInformation {

	//choose the Browser used for testing
	FirefoxDriver driver = new FirefoxDriver();
	
	
	@BeforeClass
	//launch the application
	public void init() throws InterruptedException {
		driver.manage().window().maximize();;
		driver.navigate().to("http://192.168.178.227:8080/");
		Thread.sleep(1000);
	}
	
	@Test(priority = 1)
	//login in to the application without remembering the login information
	public void Login() throws InterruptedException {			
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).click();		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCases.Constants.User);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCases.Constants.Password);
		driver.findElement(By.xpath("//*[@id=\"rememberMe\"]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 2)
	//open account information
	public void AccountInformation() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(500);
		
		//check that page account information is opened
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/h2")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("User settings for [admin]")) {
			System.out.println("Account information is visible");
		}
		else {
			System.out.println("Account information is not visible");
		}
		
		//check that user account information is correct
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).getAttribute("value");
		TestCases.Constants.SecondStatus = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).getAttribute("value");
		TestCases.Constants.ActualEmail = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).getAttribute("value");
		TestCases.Constants.ActualLanguage = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[4]/select")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase(TestCases.Constants.FirstName) && 
			TestCases.Constants.SecondStatus.equalsIgnoreCase(TestCases.Constants.LastName) &&
			TestCases.Constants.ActualEmail.equalsIgnoreCase(TestCases.Constants.Email) &&
			TestCases.Constants.ActualLanguage.equalsIgnoreCase("English")) {
				System.out.println("Account information is correct");
		}
		else {
			System.out.println("Account information is not correct");
		}		
	}
	
	@Test(priority = 3)
	//change all account information and save changes
	public void ChangeAccountInformation() throws InterruptedException {
		//change all account information and same changes
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewUser);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewUser);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewEmail);
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[3]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("An error has occurred!")) {
			System.out.println("Account information can't be changed");
		}
		else {
			System.out.println("Original account information can be changed");
		}
		
		//change back all account information and save
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.FirstName);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.LastName);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.Email);
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Settings saved!")) {
			System.out.println("Original account information can be saved");
		}
		else {
			System.out.println("Account information can't be changed to original information");
		}
	}
	
	@Test(priority = 4)
	//check allowed data format for First Name
	public void ChangeAccountFirstName() throws InterruptedException {
		//check that field first name is mandatory
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your first name is required.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		
		//check if special characters can be added as first name
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.Special);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("hidden");
		if (TestCases.Constants.Status != null) {
			System.out.println("The choosen First Name is not good");
		}
		else {
			System.out.println("The choosen First Name is good");			
		}
		
		//check if a long first name is allowed
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(200);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewUserWrong3);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("hidden");
		if (TestCases.Constants.Status != null) {
			System.out.println("The choosen First Name is not good");
		}
		else {
			System.out.println("The choosen First Name is good");			
		}
		
		//check if first name can be made out of numbers and letters
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(200);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[1]")).getAttribute("hidden");
		if (TestCases.Constants.Status != null) {
			System.out.println("The choosen First Name is not good");
		}
		else {
			System.out.println("The choosen First Name is good");			
		}		
	}
	
	@Test(priority = 5)
	//check allowed data format for Last Name
	public void ChangeAccountLastName() throws InterruptedException {
		//check that field last name is mandatory
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your last name is required.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		
		//check if special characters can be added as last name
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.Special);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("hidden");
		if (TestCases.Constants.Status != null) {
			System.out.println("The choosen Last Name is not good");
		}
		else {
			System.out.println("The choosen Last Name is good");			
		}
		
		//check if a long last name is allowed
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(200);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewUserWrong3);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("hidden");
		if (TestCases.Constants.Status != null) {
			System.out.println("The choosen Last Name is not good");
		}
		else {
			System.out.println("The choosen Last Name is good");			
		}
		
		//check if last name can be made out of numbers and letters
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(200);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[1]")).getAttribute("hidden");
		if (TestCases.Constants.Status != null) {
			System.out.println("The choosen Last Name is not good");
		}
		else {
			System.out.println("The choosen Last Name is good");			
		}
	}
	
	@Test(priority = 6)
	//check allowed data format for Email address
	public void ChangeAccountEmail() throws InterruptedException {
		//check that field email is mandatory
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[1]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your e-mail is required.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		
		//check if email without @xxxx.com can be added
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewEmailWrong1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your e-mail is invalid.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
		
		//check if a short text can be added as an email
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewEmailWrong2);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[2]")).getAttribute("innerText");
		TestCases.Constants.SecondStatus = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[3]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your e-mail is invalid.") && TestCases.Constants.SecondStatus.equalsIgnoreCase("Your e-mail is required to be at least 5 characters.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
		
		//check if a mistake in a correct email is detected
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewEmailWrong3);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your e-mail is invalid.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);		
	}
	
	@Test(priority = 7)
	//check available languages
	public void ChangeAccountLanguage() {
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[4]/select")).getAttribute("length");
		if (TestCases.Constants.Status.contentEquals("1")) {
			System.out.println("Language can't be changed");
		}
		else {
			System.out.println("There would be code here to check the language change");
		}
		
	}
	
	@Test(priority = 8)
	//logout from the application
	public void Logout() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/ul/li[4]/a/span[2]")).click();
		Thread.sleep(1000);
		TestCases.Constants.Loggout = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/h1")).getAttribute("innerText");
		if (TestCases.Constants.Loggout.equalsIgnoreCase("Welcome to Gurukula!")) {
			System.out.println("You have logged out");
		}
		else {
			System.out.println("Log out didn't work");
		}
		Thread.sleep(1000);
	}
	
	@AfterClass
	//close browser
	public void quit() {
		driver.close();
	}	
}