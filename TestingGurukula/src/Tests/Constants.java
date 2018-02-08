package Tests;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Constants {
	
	//excel location and excel variables
	public static final String ExcelLocation = System.getProperty("user.home") + "\\Documents\\Results.xlsx";
	public static final XSSFWorkbook workbook = new XSSFWorkbook();
		
	//login information
	public static final String User = "admin";
	public static final String Password = "admin";
	
	//branch information
	public static final String Branch = "Software";
	public static final String Code = "123456";
	
	//staff information
	public static final String Staff = "Developer";
	
	//new user information
	public static final String NewUser = "test";
	public static final String NewPassword = "testing";
}
