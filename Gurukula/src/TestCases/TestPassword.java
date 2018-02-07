package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPassword {
	

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
	//navigate to account settings and checks that it is the correct page
	public void PasswordInformation() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/ul/li[2]/a/span[2]")).click();
		Thread.sleep(500);
		
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/h2")).getAttribute("textContent");
		if (TestCases.Constants.Status.equalsIgnoreCase("Password for [admin]")) {
			System.out.println("The password change window has opened correctly");
		}
		else {
			System.out.println("The password change window hasn't opened correctly");
		}	
	}
	
	@Test(priority = 3)
	//checks what data types are allowed to be set in a password
	public void PasswordChange() throws InterruptedException {
		//checks that password field is mandatory
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[1]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your password is required.")) {
			System.out.println("A warning has appered");
		}
		else {
			System.out.println("A warning should have apperead");
		}
		Thread.sleep(500);
		
		//checks if password can be changed
		//application doesn't allow password to be change, but this test is considered passed
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("An error has occurred!")) {
			System.out.println("The password can't be changed");
		}
		else {
			System.out.println("The password shouldn't be changeable");
		}
		Thread.sleep(500);
		
		//checks if password can be change to initial password
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.Password);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.Password);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("An error has occurred!")) {
			System.out.println("The password can't be changed to original one");
		}
		else {
			System.out.println("The password shouldn't be changeable");
		}
		Thread.sleep(500);
		
		//checks if letters and numbers can be set as a password
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status != null) {
			System.out.println("The password you choose has the correct format");
		}
		else {
			System.out.println("The password you choose doesn't have the correct format");
		}
		Thread.sleep(500);
		
		//checks if upper case characters are allowed in password
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewPasswordGood2);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status != null) {
			System.out.println("The password you choose has the correct format");
		}
		else {
			System.out.println("The password you choose doesn't have the correct format");
		}
		Thread.sleep(500);
		
		//checks if special characters are allowed in password
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.Special);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status != null) {
			System.out.println("The password you choose has the correct format");
		}
		else {
			System.out.println("The password you choose doesn't have the correct format");
		}
		Thread.sleep(500);
		
		//checks if a short password can be set
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewPasswordWrong1);
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/div[1]/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Your password is required to be at least 5 characters.")) {
			System.out.println("A warning message has appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
		
		//checks if a different confirmation password then the password can be saved
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[1]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/div[2]/input")).sendKeys(TestCases.Constants.NewPasswordGood2);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[3]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("The password and its confirmation do not match!")) {
			System.out.println("A warning message has appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		Thread.sleep(500);
	}
	
	@Test(priority = 4)
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
	//closes browser
	public void quit() {
		driver.close();
	}
}
