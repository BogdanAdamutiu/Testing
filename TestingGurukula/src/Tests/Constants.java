package Tests;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Constants {
	
	//excel location
	public static final String ExcelLocation = System.getProperty("user.home") + "\\Documents\\Results.xlsx";
	
	//initialise browser
	public static final FirefoxDriver Mozila = new FirefoxDriver();
	
	//login information
	public static final String User = "admin";
	public static final String Password = "admin";
	
	//branch information
	public static final String Branch = "Software";
	public static final String Code = "123456";
}
