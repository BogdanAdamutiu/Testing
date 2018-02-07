package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestEdit {
	
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
	//checks that branch(name and code) information can be changed
	public void EditBranch() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[2]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.BranchNameEdit);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(TestCases.Constants.BranchCodeEdit);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		TestCases.Constants.BranchNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		TestCases.Constants.BranchCodeView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (TestCases.Constants.BranchNameView.equalsIgnoreCase(TestCases.Constants.BranchName + TestCases.Constants.BranchNameEdit) && TestCases.Constants.BranchCodeView.equalsIgnoreCase(TestCases.Constants.BranchCode + TestCases.Constants.BranchCodeEdit)) {
			System.out.println("The Branch has been edited successfully");
		}
		else {
			System.out.println("The Branch hasn't been edited");
		}
	}
	
	@Test(priority = 3)
	//checks that staff information can be changed
	//also checks that branch information changes automatically when branch is edited
	public void EditStaff() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[2]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.StaffNameEdit);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		TestCases.Constants.StaffNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		TestCases.Constants.BranchNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (TestCases.Constants.StaffNameView.equalsIgnoreCase(TestCases.Constants.StaffName + TestCases.Constants.StaffNameEdit) && TestCases.Constants.BranchNameView.equalsIgnoreCase(TestCases.Constants.BranchName + TestCases.Constants.BranchNameEdit)) {
			System.out.println("The Staff has been edited successfully");
		}
		else {
			System.out.println("The new Staff hasn't been edited");
		}		
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
