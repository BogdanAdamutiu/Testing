package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestQuery {

	FirefoxDriver driver = new FirefoxDriver();
	
	
	@BeforeClass
	//open browser and launch the application
	public void init() throws InterruptedException {
		driver.manage().window().maximize();;
		driver.navigate().to("http://192.168.178.227:8080/");
		Thread.sleep(1000);
	}
	
	@Test(priority = 1)
	//login in to the application without remembering the login information
	//creates 4 branches and 4 staffs that are latter used in the test case
	public void PrepareData() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).click();		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCases.Constants.User);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCases.Constants.Password);
		driver.findElement(By.xpath("//*[@id=\"rememberMe\"]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.BranchName);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(TestCases.Constants.BranchCode);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.BranchNameSecond);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(TestCases.Constants.BranchCodeSecond);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.BranchNameThird);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(TestCases.Constants.BranchCode);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.BranchNameFourth);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(TestCases.Constants.BranchCodeSecond);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);	

		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(500);		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.StaffName);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[2]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(500);	
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.StaffNameSecond);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[3]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.StaffNameThird);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[2]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(TestCases.Constants.StaffNameFourth);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select/option[5]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);		
	}
	
	@Test(priority = 2)
	//checks if the branch query works
	public void QueryBranch() throws InterruptedException {
		//opens branch page
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(2000);
		
		//checks if branch query works by ID
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys("2");
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		TestCases.Constants.Branch = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		TestCases.Constants.BranchNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		if (TestCases.Constants.BranchNameView.equalsIgnoreCase(TestCases.Constants.BranchName) && TestCases.Constants.Branch.equalsIgnoreCase("1")) {
			System.out.println("Branch query works");
		}
		else {
			System.out.println("Branch query doesn't work");
		}
		
		//checks if branch query works by name
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(TestCases.Constants.BranchName);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		TestCases.Constants.Branch = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		TestCases.Constants.BranchNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		if (TestCases.Constants.BranchNameView.equalsIgnoreCase(TestCases.Constants.BranchName) && TestCases.Constants.Branch.equalsIgnoreCase("1")) {
			System.out.println("Branch query works");
		}
		else {
			System.out.println("Branch query doesn't work");
		}
		
		//checks if branch query works by code
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(TestCases.Constants.BranchCodeSecond);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		TestCases.Constants.BranchCodeView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		TestCases.Constants.Branch = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		TestCases.Constants.BranchNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr[2]/td[2]")).getAttribute("innerText");
		if (TestCases.Constants.Branch.equalsIgnoreCase(TestCases.Constants.BranchNameSecond) && 
			TestCases.Constants.BranchNameView.equalsIgnoreCase(TestCases.Constants.BranchNameFourth) && 
			TestCases.Constants.BranchCodeView.equalsIgnoreCase("2")) {
			System.out.println("Branch query works");
		}
		else {
			System.out.println("Branch query doesn't work");
		}		
	}
	
	@Test(priority = 3)
	//checks if the staff query works
	public void QueryStaff() throws InterruptedException {
		//opens staff page
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(2000);
		
		//checks if staff query works by ID
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys("2");
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		TestCases.Constants.Staff = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		TestCases.Constants.StaffNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		if (TestCases.Constants.Staff.equalsIgnoreCase("1") && TestCases.Constants.StaffNameView.equalsIgnoreCase(TestCases.Constants.StaffNameSecond)) {
			System.out.println("Staff query works");
		}
		else {
			System.out.println("Staff query should have worked when searching for ID");
		}
		
		//checks if branch query works by name
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(TestCases.Constants.StaffNameThird);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		TestCases.Constants.Staff = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		TestCases.Constants.StaffNameView = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		if (TestCases.Constants.Staff.equalsIgnoreCase("1") && TestCases.Constants.StaffNameView.equalsIgnoreCase(TestCases.Constants.StaffNameThird)) {
			System.out.println("Staff query works");
		}
		else {
			System.out.println("Staff query doesn't work");
		}
		
		//checks if branch query works by branch name
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(TestCases.Constants.BranchName);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		TestCases.Constants.Staff = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		if (TestCases.Constants.Staff.equalsIgnoreCase("0")) {
			System.out.println("Staff query doesn't work when searching over Branch name");
		}
		else {
			System.out.println("Staff query shouldn't work when searching over Branch name");
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
