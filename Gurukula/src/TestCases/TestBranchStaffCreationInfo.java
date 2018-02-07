package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBranchStaffCreationInfo {

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
	//checks data format allowed for branch creation
	public void BranchCreationInfo() throws InterruptedException {
		//opens branch page
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);
		
		//checks if name of branch is a mandatory field
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[1]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field is required.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		
		//checks if a short name can be added for a branch
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.NewEmailWrong2);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field is required to be at least 5 characters.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		
		//checks if special characters can be added as a name of branch
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.Special);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field should follow pattern ^[a-zA-Z\\\\s]*$.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		
		//checks if a long name can be added as a name of branch
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.NewUserWrong3);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[3]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field cannot be longer than 20 characters.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		
		//checks if numbers and letters can be added as name of branch
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field should follow pattern ^[a-zA-Z\\\\s]*$.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		
		//checks if code field is mandatory
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/div/p[1]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field is required.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		
		//checks if letters can be added as a code of branch
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(TestCases.Constants.NewEmailWrong2);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/div/p[4]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field should follow pattern ^[A-Z0-9]*$.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		
		//checks if special characters can make the code of a branch
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(TestCases.Constants.Special);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/div/p[4]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field should follow pattern ^[A-Z0-9]*$.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		
		//checks if a single number can be set as code of a branch
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(TestCases.Constants.Short);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/div/p[2]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field is required to be at least 2 characters.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[1]")).click();
		Thread.sleep(1500);
	}
	
	@Test(priority = 3)
	//checks data format allowed for staff creation
	public void StaffCreationInfo() throws InterruptedException {
		//opens staff page
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(2000);		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1500);
		
		//checks if staff name field is mandatory
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[1]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field is required.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		Thread.sleep(500);
		
		//checks if numbers and letters can make the name of a staff
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.NewPasswordGood1);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field should follow pattern ^[a-zA-Z\\\\s]*$.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		Thread.sleep(500);
		
		//checks if special characters are allowed in the name of a staff
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.Special);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field should follow pattern ^[a-zA-Z\\\\s]*$.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		Thread.sleep(500);
		
		//checks if a long name can be set as staff name
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.NewUserWrong3);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[3]")).getAttribute("innerText");
		if (TestCases.Constants.Status.equalsIgnoreCase("This field cannot be longer than 50 characters.")) {
			System.out.println("A warning has appeared");
		}
		else {
			System.out.println("A warning should have appeared");
		}
		Thread.sleep(500);
		
		//checks that a single letter can be set as name for staff
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys("T");
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).getAttribute("disabled");
		if (TestCases.Constants.Status != null) {
			System.out.println("There should be no warning");
		}
		else {
			System.out.println("The Name can be as short as one letter");
		}
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[1]")).click();
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