package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class ReusableAcctions {
	
	public void StartFirefox() {		
		Tests.Constants.Mozila.manage().window().maximize();;
		Tests.Constants.Mozila.navigate().to("http://192.168.178.227:8080/");
		Tests.Constants.Mozila.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void Login(String User, String Password) throws InterruptedException {
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).click();		
		Thread.sleep(1000);
		
		Tests.Constants.Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(User);
		Tests.Constants.Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Password);
		Tests.Constants.Mozila.findElement(By.xpath("//*[@id=\"rememberMe\"]")).click();
		Tests.Constants.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(2000);
	}
	
	
}
