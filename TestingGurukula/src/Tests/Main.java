package Tests;

import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//create the results excel file
		ReusableActions Test = new ReusableActions();
		Test.ResultsFile(Tests.Constants.ExcelLocation);
		
		//Login Information
		//This test case will test the login
		DriverReusableActions Test1 = new DriverReusableActions();
		Test1.OpenBrowser();
		Test1.Login("", "", false, Tests.Constants.TestCases[0]);
		Test1.Login(Tests.Constants.User, "", false, Tests.Constants.TestCases[0]);
		Test1.Login("", Tests.Constants.Password, false, Tests.Constants.TestCases[0]);
		Test1.Login("Wrong", "Wrong", false, Tests.Constants.TestCases[0]);
		Test1.Login(Tests.Constants.User, "Wrong", false, Tests.Constants.TestCases[0]);
		Test1.Login("Wrong", Tests.Constants.Password, false, Tests.Constants.TestCases[0]);
		Test1.Login(Tests.Constants.User, Tests.Constants.Password, true, Tests.Constants.TestCases[0]);
		//get cookies
		Set<Cookie> All = Test1.Mozila.manage().getCookies();
		Test1.Close();
		DriverReusableActions Test11 = new DriverReusableActions();
		Test11.OpenBrowser();
		for (Cookie cookie : All) {
			Test11.Mozila.manage().addCookie(cookie);
		}
		Test11.Mozila.navigate().refresh();
		Thread.sleep(2000);
		if (Test11.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div")).isDisplayed()) {
			Test11.ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", "Remember me", Tests.Constants.TestCases[0]);
			Test11.Close();
		}
		else {
			Test11.ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", "Remember me", Tests.Constants.TestCases[0]);
			Test11.Close();
		}		
		
		//Password Change
		//This test case will test the functionality of password change
		DriverReusableActions Test2 = new DriverReusableActions();
		Test2.OpenBrowser();
		Test2.Login(Tests.Constants.User, Tests.Constants.Password, false, Tests.Constants.TestCases[1]);
		Test2.NavigateToPasswordChange();
		Test2.ChangePassword("", "", Tests.Constants.TestCases[1]);
		Test2.CheckPassword(Tests.Constants.Short, Tests.Constants.TestCases[1]);
		Test2.CheckPassword(Tests.Constants.Long, Tests.Constants.TestCases[1]);
		Test2.CheckPassword(Tests.Constants.Upper, Tests.Constants.TestCases[1]);
		Test2.CheckPassword(Tests.Constants.Special, Tests.Constants.TestCases[1]);
		Test2.CheckPassword(Tests.Constants.Combined, Tests.Constants.TestCases[1]);
		Test2.ChangePassword(Tests.Constants.Password, Tests.Constants.NewPassword, Tests.Constants.TestCases[1]);
		Test2.ChangePassword(Tests.Constants.NewPassword, Tests.Constants.NewPassword, Tests.Constants.TestCases[1]);
		Test2.ChangePassword(Tests.Constants.Password, Tests.Constants.Password, Tests.Constants.TestCases[1]);
		Test2.Close();
		
		//New User Registration
		//This test case will try to registering a new user		
		DriverReusableActions Test3 = new DriverReusableActions();
		Test3.OpenBrowser();
		Test3.RegisterUser(Tests.Constants.NewUser, Tests.Constants.NewEmail, Tests.Constants.NewPassword, Tests.Constants.NewPassword, Tests.Constants.TestCases[2]);
		Test3.RegisterUser(Tests.Constants.Short, Tests.Constants.Mistake, Tests.Constants.Short, Tests.Constants.Short, Tests.Constants.TestCases[2]);
		Test3.RegisterUser(Tests.Constants.Long, Tests.Constants.Incomplete, Tests.Constants.Long, Tests.Constants.Long, Tests.Constants.TestCases[2]);
		Test3.RegisterUser(Tests.Constants.Upper, Tests.Constants.Short, Tests.Constants.Upper, Tests.Constants.Upper, Tests.Constants.TestCases[2]);
		Test3.RegisterUser(Tests.Constants.Special, "", Tests.Constants.Special, Tests.Constants.Special, Tests.Constants.TestCases[2]);
		Test3.RegisterUser(Tests.Constants.Combined, Tests.Constants.NewEmail, Tests.Constants.Combined, Tests.Constants.Combined, Tests.Constants.TestCases[2]);
		Test3.RegisterUser(Tests.Constants.Combined, Tests.Constants.NewEmail, Tests.Constants.Password, Tests.Constants.NewPassword, Tests.Constants.TestCases[2]);
		Test3.RegisterUser(Tests.Constants.User, Tests.Constants.InitialEmail, Tests.Constants.Password, Tests.Constants.Password, Tests.Constants.TestCases[2]);
		Test3.Close();

		//Account Information
		//This test case will test the account information page
		DriverReusableActions Test4 = new DriverReusableActions();
		Test4.OpenBrowser();
		Test4.Login(Tests.Constants.User, Tests.Constants.Password, false, Tests.Constants.TestCases[3]);
		Test4.NavigateToAccountInformation();
		Test4.FirstName("", Tests.Constants.TestCases[3]);
		Test4.FirstName(Tests.Constants.Short, Tests.Constants.TestCases[3]);
		Test4.FirstName(Tests.Constants.Long, Tests.Constants.TestCases[3]);
		Test4.FirstName(Tests.Constants.Upper, Tests.Constants.TestCases[3]);
		Test4.FirstName(Tests.Constants.Special, Tests.Constants.TestCases[3]);
		Test4.FirstName(Tests.Constants.Combined, Tests.Constants.TestCases[3]);
		Test4.LastName("", Tests.Constants.TestCases[3]);
		Test4.LastName(Tests.Constants.Short, Tests.Constants.TestCases[3]);
		Test4.LastName(Tests.Constants.Long, Tests.Constants.TestCases[3]);
		Test4.LastName(Tests.Constants.Upper, Tests.Constants.TestCases[3]);
		Test4.LastName(Tests.Constants.Special, Tests.Constants.TestCases[3]);
		Test4.LastName(Tests.Constants.Combined, Tests.Constants.TestCases[3]);
		Test4.Email("", Tests.Constants.TestCases[3]);
		Test4.Email(Tests.Constants.Short, Tests.Constants.TestCases[3]);
		Test4.Email(Tests.Constants.Incomplete, Tests.Constants.TestCases[3]);
		Test4.Email(Tests.Constants.Mistake, Tests.Constants.TestCases[3]);
		Test4.Logout(Tests.Constants.TestCases[3]);
		Test4.Close();
		
		//Data format for Branch and Staff
		//This test case will test what can be set as name and name for branch and staff
		DriverReusableActions Test5 = new DriverReusableActions();
		Test5.OpenBrowser();
		Test5.Login(Tests.Constants.User, Tests.Constants.Password, false, Tests.Constants.TestCases[4]);
		Test5.NavigateToBranch();
		Test5.CreateBranch("", "", Tests.Constants.TestCases[4]);
		Test5.CreateBranch(Tests.Constants.Short, Tests.Constants.Short, Tests.Constants.TestCases[4]);
		Test5.CreateBranch(Tests.Constants.Long, Tests.Constants.LongNumber, Tests.Constants.TestCases[4]);
		Test5.CreateBranch(Tests.Constants.Upper, "2", Tests.Constants.TestCases[4]);
		Test5.CreateBranch(Tests.Constants.Special, Tests.Constants.Special, Tests.Constants.TestCases[4]);
		Test5.CreateBranch(Tests.Constants.Combined, Tests.Constants.Combined, Tests.Constants.TestCases[4]);
		Test5.CreateBranch(Tests.Constants.Branch, Tests.Constants.Code, Tests.Constants.TestCases[4]);
		Test5.NavigateToStaff();
		Test5.CreateStaff("", Tests.Constants.Branch, Tests.Constants.TestCases[4]);
		Test5.CreateStaff(Tests.Constants.Short, Tests.Constants.Branch, Tests.Constants.TestCases[4]);
		Test5.CreateStaff(Tests.Constants.Long, Tests.Constants.Branch, Tests.Constants.TestCases[4]);
		Test5.CreateStaff(Tests.Constants.Upper, Tests.Constants.Branch, Tests.Constants.TestCases[4]);
		Test5.CreateStaff(Tests.Constants.Special, Tests.Constants.Branch, Tests.Constants.TestCases[4]);
		Test5.CreateStaff(Tests.Constants.Combined, Tests.Constants.Branch, Tests.Constants.TestCases[4]);
		Test5.CreateStaff("t", Tests.Constants.Branch, Tests.Constants.TestCases[4]);
		Test5.Logout(Tests.Constants.TestCases[4]);
		Test5.Close();
			
		//Create
		//This test case will test if a branch and a staff can be create
		DriverReusableActions Test6 = new DriverReusableActions();
		Test6.OpenBrowser();
		Test6.Login(Tests.Constants.User, Tests.Constants.Password, false, Tests.Constants.TestCases[5]);
		Test6.NavigateToBranch();
		Test6.CreateBranch("Logistics", "4567", Tests.Constants.TestCases[5]);
		Test6.CreateBranch("Logistics", "4567", Tests.Constants.TestCases[5]);
		Test6.NavigateToStaff();
		Test6.CreateStaff(Tests.Constants.Staff, "Logistics", Tests.Constants.TestCases[5]);
		Test6.CreateStaff(Tests.Constants.Staff, "Logistics", Tests.Constants.TestCases[5]);
		Test6.Logout(Tests.Constants.TestCases[5]);
		Test6.Close();
		
		//View
		//This test case will test the view action
		DriverReusableActions Test7 = new DriverReusableActions();
		Test7.OpenBrowser();
		Test7.Login(Tests.Constants.User, Tests.Constants.Password, false, Tests.Constants.TestCases[6]);
		Test7.NavigateToBranch();
		Test7.ViewBranch(Tests.Constants.Branch, Tests.Constants.Code, Tests.Constants.TestCases[6]);
		Test7.NavigateToStaff();
		Test7.ViewStaff(Tests.Constants.Staff, Tests.Constants.Branch, Tests.Constants.TestCases[6]);
		Test7.Logout(Tests.Constants.TestCases[6]);
		Test7.Close();
		
		//Edit
		//This test case will test the edit action
		DriverReusableActions Test8 = new DriverReusableActions();
		Test8.OpenBrowser();
		Test8.Login(Tests.Constants.User, Tests.Constants.Password, false, Tests.Constants.TestCases[7]);
		Test8.NavigateToBranch();
		Test8.EditBranch(Tests.Constants.Branch, Tests.Constants.NewBranch, Tests.Constants.Code, Tests.Constants.NewCode, Tests.Constants.TestCases[7]);
		Test8.NavigateToStaff();
		Test8.EditStaff(Tests.Constants.Staff, Tests.Constants.NewStaff, Tests.Constants.Branch, Tests.Constants.NewBranch, Tests.Constants.TestCases[7]);
		Test8.Logout(Tests.Constants.TestCases[7]);
		Test8.Close();
		
		//Delete
		//This test case will test the delete action
		DriverReusableActions Test9 = new DriverReusableActions();
		Test9.OpenBrowser();
		Test9.Login(Tests.Constants.User, Tests.Constants.Password, false, Tests.Constants.TestCases[8]);
		Test9.NavigateToBranch();
		Test9.DeteleBranch(Tests.Constants.Branch, Tests.Constants.Code, Tests.Constants.TestCases[8]);
		Test9.NavigateToStaff();
		Test9.DeleteStaff(Tests.Constants.Staff, Tests.Constants.Branch, Tests.Constants.TestCases[8]);
		Test9.NavigateToBranch();
		Test9.DeteleBranch(Tests.Constants.Branch, Tests.Constants.Code, Tests.Constants.TestCases[8]);
		Test9.Logout(Tests.Constants.TestCases[8]);
		Test9.Close();
		
		//Query
		//This test case will test the query action
		DriverReusableActions Test10 = new DriverReusableActions();
		Test10.OpenBrowser();
		Test10.Login(Tests.Constants.User, Tests.Constants.Password, false, Tests.Constants.TestCases[9]);
		Test10.NavigateToBranch();
		Test10.CreateBranch("Management", "13579", Tests.Constants.TestCases[9]);
		Test10.CreateBranch("Finance", "2468", Tests.Constants.TestCases[9]);
		Test10.NavigateToStaff();
		Test10.CreateStaff("Accountant", "Finance", Tests.Constants.TestCases[9]);
		Test10.CreateStaff("Manager", "Management", Tests.Constants.TestCases[9]);
		Test10.NavigateToBranch();
		Test10.QueryBranch("3", Tests.Constants.TestCases[9]);
		Test10.QueryBranch("Marketing", Tests.Constants.TestCases[9]);
		Test10.QueryBranch("4578", Tests.Constants.TestCases[9]);
		Test10.QueryBranch("Inexistent", Tests.Constants.TestCases[9]);
		Test10.NavigateToStaff();
		Test10.QueryStaff("Tester", Tests.Constants.TestCases[9]);
		Test10.QueryStaff("2", Tests.Constants.TestCases[9]);
		Test10.QueryStaff("Development", Tests.Constants.TestCases[9]);
		Test10.Logout(Tests.Constants.TestCases[9]);
		Test10.Close();		
	}

}
