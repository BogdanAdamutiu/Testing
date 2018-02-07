package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;

public class ReusableAcctions {
	
	public void StartFirefox() {
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();;
		driver.navigate().to("http://192.168.178.227:8080/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//return "Passed";
	}
	
	
}
