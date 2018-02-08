package Tests;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Constants {
	//excel location
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
	public static final String NewUser = "testing";
	public static final String NewPassword = "testing";
	public static final String Email = "test@yahoo.com"; 
		
	//constants for testing data type
	public static final String Short = "test";
	public static final String Upper = "TESTING";
	public static final String Combined = "1t2e3s4t";
	public static final String Special = "!@#$%";
	public static final String Long = "thisusernameshouldbetolongtobeausernamewithmorethen";
		
	//constants used for testing email address
	public static final String Incomplete = "test@";
	public static final String Mistake = "test@yahoo,com";
		
	//account information
	public static final String FirstName = "Administrator";
	public static final String LastName = "Administrator";
	public static final String InitialEmail = "admin@localhost";
	
	//new information for branch and staff
	public static final String NewBranch = "Finance";
	public static final String NewCode = "345678";
	public static final String NewStaff = "Accountant";
}
