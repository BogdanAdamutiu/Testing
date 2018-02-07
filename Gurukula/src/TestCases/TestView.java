package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestView {
	
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
	//checks that a branch information can be seen
	public void ViewBranch() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[1]")).click();
		Thread.sleep(1000);
		
		TestCases.Constants.Branch = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/span")).getAttribute("innerText");
		TestCases.Constants.BranchNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[1]/td[2]/input")).getAttribute("defaultValue");
		TestCases.Constants.BranchCodeView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[2]/td[2]/input")).getAttribute("defaultValue");
		if (TestCases.Constants.Branch.equalsIgnoreCase("Branch") && (TestCases.Constants.BranchNameView.equalsIgnoreCase(TestCases.Constants.BranchName)) && TestCases.Constants.BranchCodeView.equalsIgnoreCase(TestCases.Constants.BranchCode)) {
			System.out.println("The previous created Branch is viewable");
		}
		else {
			System.out.println("The previous created Branch isn't viewable");
		}
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/button")).click();
	}
	
	@Test(priority = 3)
	//checks that a staff information can be seen
	public void ViewStaff() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[1]")).click();
		Thread.sleep(1000);
		
		TestCases.Constants.Staff = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/span")).getAttribute("innerText");
		TestCases.Constants.StaffNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[1]/td[2]/input")).getAttribute("defaultValue");
		TestCases.Constants.StaffBranch = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[2]/td[2]/input")).getAttribute("defaultValue");
		if (TestCases.Constants.Staff.equalsIgnoreCase("Staff") && TestCases.Constants.StaffNameView.equalsIgnoreCase(TestCases.Constants.StaffName) && TestCases.Constants.StaffBranch.equalsIgnoreCase(TestCases.Constants.BranchName)) {
			System.out.println("The previous created Staff is viewable");
		}
		else {
			System.out.println("The previous created Staff isn't viewable");
		}
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/button")).click();		
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