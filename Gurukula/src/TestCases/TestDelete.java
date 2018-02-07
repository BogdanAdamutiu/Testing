package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestDelete {

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
	//delete branch before deleting staff attached to branch
	public void DeteleBranchFirst() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		TestCases.Constants.Status = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).getAttribute("disabled");
		if (TestCases.Constants.Status != null) {
			System.out.println("There has been an error, branches can't be delete if staff are assigned to it");
		}
		else {
			System.out.println("Branch can't be deleted if a staff is assigned to it");
			driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[1]")).click();
			Thread.sleep(1000);
		}
	}
	
	@Test(priority = 3)
	//deletes a staff
	public void DeleteStaff() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(500);				
	}
		
	@Test(priority = 4)
	//deletes a branch
	public void DeleteBranch() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
	}
	
	@Test(priority = 5)
	//logout from the application
	public void Logout() throws InterruptedException {
		Thread.sleep(1500);
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
