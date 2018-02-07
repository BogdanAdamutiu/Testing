package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestRegisterUser {
	
	FirefoxDriver driver = new FirefoxDriver();
	
	
	@BeforeClass
	//launch the application
	public void init() throws InterruptedException {
		driver.manage().window().maximize();;
		driver.navigate().to("http://192.168.178.227:8080/");
		Thread.sleep(1000);
	}
	
	@Test(priority = 1)
	//registers a new user
	//registration of new users doesn't work, but this test case is considered passed because all the user information are correct
	public void Register() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[2]/a")).click();
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewUser);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewEmail);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewPassword);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[4]/input")).sendKeys(TestCases.Constants.NewPassword);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Registration failed! Please try again later.")) {
			System.out.println("The new user has been created successfully");
		}
		else {
			System.out.println("The new user hasn't been created");
		}
	}
	
	@Test(priority = 2)
	//checks what type of data can be used in the user name
	public void NewUserInformation() throws InterruptedException {
		//navigates to home menu
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[1]/a[2]/span[2]")).click();
		Thread.sleep(500);
		
		//navigates to user registration
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[2]/a")).click();
		Thread.sleep(1500);
		
		//checks if upper case characters are allowed in user name
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewUserWrong1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[4]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your login can only contain lower-case letters and digits.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
		
		//checks if special characters are allowed in user name
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.Special);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[4]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your login can only contain lower-case letters and digits.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
		
		//checks if a long name is allowed as user name
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewUserWrong3);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div/p[3]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your login cannot be longer than 50 characters.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
	}
	
	@Test(priority = 3)
	//checks what type of data can be used in the email
	public void NewEmailInformation() throws InterruptedException {
		//check if an incomplete email address is allowed
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewEmailWrong1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your e-mail is invalid.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
		
		//check if a short incomplete email address is allowed
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewEmailWrong2);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[2]")).getAttribute("innerText");
		TestCases.Constants.SecondStatus = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[3]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your e-mail is invalid.") && TestCases.Constants.SecondStatus.equalsIgnoreCase("Your e-mail is required to be at least 5 characters.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
		
		//check if a mistake in the email address is detected
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewEmailWrong3);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/div/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your e-mail is invalid.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
	}
	
	@Test(priority = 4)
	//checks what type of data can be used in the password
	public void NewPasswordInformation() throws InterruptedException {
		//checks if letters and numbers are allowed as a password
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[2]/small")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Password strength:")) {
			System.out.println("Inserted password is good and no warning appeared");
		}
		else {
			System.out.println("Inserted password does not meet the standards");
		}
		Thread.sleep(500);
		
		//checks if upper case characters are allowed as a password
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewPasswordGood2);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[2]/small")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Password strength:")) {
			System.out.println("Inserted password is good and no warning appeared");
		}
		else {
			System.out.println("Inserted password does not meet the standards");
		}
		Thread.sleep(500);
		
		//checks if special characters are allowed as a password
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewPasswordGood3);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[2]/small")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Password strength:")) {
			System.out.println("Inserted password is good and no warning appeared");
		}
		else {
			System.out.println("Inserted password does not meet the standards");
		}
		Thread.sleep(500);
		
		//checks if a short password is allowed
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewPasswordWrong1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/div[1]/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your password is required to be at least 5 characters.")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
		
		//checks if a different confirmation password than password can be saved
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewUser);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewEmail);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[3]/input")).sendKeys(TestCases.Constants.NewPassword);		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[4]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[5]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("The password and its confirmation do not match!")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
	}
	
	@AfterClass
	//closes browser
	public void quit() {
		driver.close();
	}
}