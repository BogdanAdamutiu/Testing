package TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin {

	FirefoxDriver driver = new FirefoxDriver();
	
	
	@BeforeClass
	//launch the application
	public void init() throws InterruptedException {
		driver.manage().window().maximize();;
		driver.navigate().to("http://192.168.178.227:8080/");
		Thread.sleep(1000);
	}
	
	@Test(priority = 1)
	//checks login data
	public void UserInformation() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).click();		
		Thread.sleep(1000);
		
		//checks if login work without user and password
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Authentication failed!")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		
		//checks if login works only with user
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCases.Constants.User);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Authentication failed!")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		
		//checks if login works only with password
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();		
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCases.Constants.Password);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Authentication failed!")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		
		//checks if login works with wrong user and wrong password
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCases.Constants.NewUser);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCases.Constants.NewPassword);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Authentication failed!")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		
		//checks if login works with good user and wrong password
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCases.Constants.User);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCases.Constants.NewPassword);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Authentication failed!")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
		
		//checks if login works with wrong user and good password
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCases.Constants.NewUser);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCases.Constants.Password);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(500);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("Authentication failed!")) {
			System.out.println("A warning message appeared");
		}
		else {
			System.out.println("A warning message should have appeared");
		}
	}
	
	@Test(priority = 2)
	//checks if user remains logged in when remember me box is checked
	public void RememberLogin() throws InterruptedException {			
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCases.Constants.User);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCases.Constants.Password);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(1000);
		
		driver.quit();
		FirefoxDriver driver = new FirefoxDriver();
		driver.navigate().to("http://192.168.178.227:8080/");
		driver.manage().window().maximize();
		//Should have code to check that user is still logged in, but the user doesn't remain logged in
		//When I do this test manually the user remain logged in
		driver.quit();
	}	
}
