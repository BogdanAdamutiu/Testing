package Tests;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		ReusableAcctions Test = new ReusableAcctions();
		Test.ResultsFile(Tests.Constants.ExcelLocation);
		Test.Close();

		//first test case
		LoginInformation Test1 = new LoginInformation();
		Test1.UserInformation();
		Test1.RememberLogin();
		
		//second test case
		PasswordChange Test2 = new PasswordChange();
		Test2.OpenPasswordInformation();
		Test2.PasswordChange();
		
		//third test case
		RegisterUser Test3 = new RegisterUser();
		Test3.Register();
		Test3.NewUserInformation();
		Test3.NewEmailInformation();
		Test3.NewPasswordInformation();
		
		//fourth test case
		AccountInformation Test4 = new AccountInformation();
		Test4.OpenAccountInformation();
		Test4.AccountInformationView();
		Test4.ChangeAccountInformation();
		Test4.ChangeAccountFirstName();
		Test4.ChangeAccountLastName();
		Test4.ChangeAccountEmail();
		Test4.ChangeAccountLanguage();
		
		//fifth test case
		BranchAndStaffCreationInfo Test5 = new BranchAndStaffCreationInfo();
		Test5.BranchCreationInfo();
		Test5.StaffCreationInfo();
		
		//sixth test case
		//test create action
		ReusableAcctions Test6 = new ReusableAcctions();
		Test6.OpenBrowser();
		Test6.Login(Tests.Constants.User, Tests.Constants.Password);
		Test6.NavigateToBranch();
		Test6.CreateBranch(Tests.Constants.Branch, Tests.Constants.Code, 6, 1, 1);
		Test6.NavigateToStaff();
		Test6.CreateStaff(Tests.Constants.Staff, 6, 2, 1);
		Test6.Logout(6, 3);
		Test6.Close();
		
		//seventh test case
		//test view action
		ReusableAcctions Test7 = new ReusableAcctions();
		Test7.OpenBrowser();
		Test7.Login(Tests.Constants.User, Tests.Constants.Password);
		Test7.NavigateToBranch();
		Test7.ViewBranch();
		Test7.NavigateToStaff();
		Test7.ViewStaff();
		Test7.Logout(7,3);
		Test7.Close();
		
		//eighth test case
		//test edit action
		ReusableAcctions Test8 = new ReusableAcctions();
		Test8.OpenBrowser();
		Test8.Login(Tests.Constants.User, Tests.Constants.Password);
		Test8.NavigateToBranch();
		Test8.EditBranch(Tests.Constants.NewBranch, Tests.Constants.NewCode, 8, 1);
		Test8.NavigateToStaff();
		Test8.EditStaff(Tests.Constants.NewStaff, 8, 2);
		Test8.Logout(8, 3);
		Test8.Close();
		
		//ninth test case
		//test delete action
		ReusableAcctions Test9 = new ReusableAcctions();
		Test9.OpenBrowser();
		Test9.Login(Tests.Constants.User, Tests.Constants.Password);
		Test9.NavigateToBranch();
		Test9.DeteleBranch(0);
		Test9.NavigateToStaff();
		Test9.DeleteStaff();
		Test9.NavigateToBranch();
		Test9.DeteleBranch(1);
		Test9.Logout(9, 4);
		Test9.Close();
		
		//tenth test case
		ReusableAcctions Test10 = new ReusableAcctions();
		Test10.OpenBrowser();
		Test10.Login(Tests.Constants.User, Tests.Constants.Password);
		Test10.NavigateToBranch();
		Test10.CreateBranch("Finance", "1234", 10, 1, 0);
		Test10.CreateBranch("Development", "4578", 10, 2, 0);
		Test10.CreateBranch("Marketing", "3456", 10, 3, 0);
		Test10.NavigateToStaff();
		Test10.CreateStaff("Accountant", 10, 4, 2);
		Test10.CreateStaff("Developer", 10, 5, 3);
		Test10.CreateStaff("Tester", 10, 6, 3);
		Test10.NavigateToBranch();
		Test10.QueryBranch("3");
		Test10.QueryBranch("Marketing");
		Test10.QueryBranch("4578");
		Test10.QueryBranch("Management");
		Test10.NavigateToStaff();
		Test10.QueryStaff("Tester");
		Test10.QueryStaff("2");
		Test10.QueryStaff("Development");
		Test10.Logout(10,7);
		Test10.Close();		
	}

}
