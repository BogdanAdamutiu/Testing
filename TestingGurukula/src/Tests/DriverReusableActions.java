package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class DriverReusableActions {
	
	//local variables
	FirefoxDriver Mozila = new FirefoxDriver();
	ReusableActions ExcelAction = new ReusableActions();
	String Status = "";
	String Results = "0";
	String SecondResults = "0";
	String IDCheck = "";
	String BranchCheck = "";	
	String CodeCheck = "";
	String StaffCheck = "";
	String BranchSecondCheck ="";
	String CodeSecondCheck = "";
	String StaffSecondCheck = "";
	String MethodName = "";
	int NrOfResults = 0;
	int SecondNrOfResults = 0;
	int FoundResult = 0;
	int Decrement = 0;
	int DeletedStaffs = 0;
	
	/** 
	* This method is used to open browser and navigate to Gurukula application
	*/
	public void OpenBrowser() {
		Mozila.manage().window().maximize();;
		Mozila.navigate().to("http://192.168.178.227:8080/");
		Mozila.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	/** 
	* This method is used to login in to Gurukula application
	* @param User This the user name
	* @param Password This is the password
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void Login(String User, String Password, String Test) throws InterruptedException, IOException {
		MethodName = "Login";
		//click on login
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")).click();		
		Thread.sleep(1000);
		
		//enter user name and password
		Mozila.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(User);
		Mozila.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Password);
		Mozila.findElement(By.xpath("//*[@id=\"rememberMe\"]")).click();
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
		Thread.sleep(2000);
		
		//check that we are logged in as user admin
		Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("You are logged in as user \"admin\".")) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", MethodName, Test);
		}
		else {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
		}
	}
	
	/** 
	* This method is used to navigate to Branch page
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	*/
	public void NavigateToBranch() throws InterruptedException {
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/b")).click();
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[1]/a/span[2]")).click();
		Thread.sleep(1000);
	}
	
	/** 
	* This method is used to navigate to Staff page
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	*/
	public void NavigateToStaff() throws InterruptedException {
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/a/span/span[2]")).click();
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[2]/ul/li[2]/a/span[2]")).click();											
		Thread.sleep(1000);
	}
	
	/** 
	* This method is used to create a new Branch
	* @param Name This is the name of the branch
	* @param Code This is the code of the branch
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void CreateBranch(String Name, String Code, String Test) throws InterruptedException, IOException {
		MethodName = "Create Branch";
		//click on "Create Branch"
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);
		
		//enter branch name and code then click save
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(Code);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
		//clear search criteria and search after the name of the branch
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		//check that there is at least one branch with the given name and code
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation,"Failed",MethodName, Test);
		}
		else if (NrOfResults >= 1) {
			for (int i = 1; i <= NrOfResults; i++) {
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				CodeCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (BranchCheck.equalsIgnoreCase(Name) && CodeCheck.equalsIgnoreCase(Code)) {
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", MethodName, Test);
				}
				else {
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
				}
			}
		}
	}
	
	/** 
	* This method is used to create a new Staff and assign it to desired Branch
	* @param Name This is the name of the staff
	* @param Branch This is the branch to which the staff will be assigned to
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void CreateStaff(String Name, String Branch, String Test) throws InterruptedException, IOException {
		MethodName = "Create Staff";
		//click on "Create Staff"
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1500);
		
		//create new staff with given name and assign it to given branch
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Name);		
		//create a select that contains all the available branches
		Select SelectBranch = new Select(Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")));		
		//open branch list
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")).click();
		Thread.sleep(500);		
		//select desired branch
		SelectBranch.selectByVisibleText(Branch);		
		//click on save
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		//clear search criteria and search after staff name 
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		//search for the new created staff and check that it has been created with the given data
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
		}
		else {
			for (int i = 1; i <= NrOfResults; i++) {
				StaffCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (StaffCheck.equalsIgnoreCase(Name) && BranchCheck.equalsIgnoreCase(Branch)) {
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", MethodName, Test);
				}
				else {
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
				}
			}
		}
	}
	
	/** 
	* This method is used to view Branch
	* @param Name This is the name of the branch
	* @param Code This is the code of the branch
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void ViewBranch(String Name, String Code, String Test) throws InterruptedException, IOException {	
		MethodName = "View Branch";
		
		//search for the branch you want to view
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		//check if a branch with the given name exists
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "You tried to view a non existing branch", MethodName, Test);
		}
		//view the branch
		else {
			for (int i = 1; i <= NrOfResults; i++) {
				//search for the branch with the desired name and code
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				CodeCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (BranchCheck.equalsIgnoreCase(Name) && CodeCheck.equalsIgnoreCase(Code)) {
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[4]/button[1]")).click();
					Thread.sleep(1000);
					//check that the page opened is the view page
					//also check if the viewed branch is the desired one
					Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/span")).getAttribute("innerText");
					BranchSecondCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[1]/td[2]/input")).getAttribute("defaultValue");
					CodeSecondCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[2]/td[2]/input")).getAttribute("defaultValue");
					if (Status.equalsIgnoreCase("Branch") && (BranchSecondCheck.equalsIgnoreCase(Name)) && CodeSecondCheck.equalsIgnoreCase(Code)) {
						ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", MethodName, Test);
					}
					else {
						ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
					}
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/button")).click();
				}
			}	
		}
	}	
	
	/** 
	* This method is used to view Staff
	* @param Name This is the name of the staff
	* @param Branch This is the branch to which the staff is assigned to
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void ViewStaff(String Staff, String Branch, String Test) throws InterruptedException, IOException {
		MethodName = "View Staff";
		
		//search for staff you want to see
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(Staff);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		//check if a staff with the given name exists
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "You tried to view a non existing staff", MethodName, Test);
		}
		//view the staff
		else {
			for (int i = 1; i <= NrOfResults; i++) {
				//search for the staff with the desired name and branch
				StaffCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (StaffCheck.equalsIgnoreCase(Staff) && BranchCheck.equalsIgnoreCase(Branch)) {
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[4]/button[1]")).click();
					Thread.sleep(1000);
					//check that the page opened is the view page
					//also check if the viewed staff is the desired one
					Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/span")).getAttribute("innerText");
					String StaffView = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[1]/td[2]/input")).getAttribute("defaultValue");
					String BranchView = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[2]/td[2]/input")).getAttribute("defaultValue");
					if (Status.equalsIgnoreCase("Staff") && StaffView.equalsIgnoreCase(Staff) && BranchView.equalsIgnoreCase(Branch)) {
						ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", MethodName, Test);
					}
					else {
						ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
					}
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/button")).click();
				}
			}			
		}
	}
	
	/** 
	* This method is used to edit Branch
	* @param Name This is the name of the branch you want to edit
	* @param NewName This is the new name you want to assign to the branch
	* @param Code This is the code of the branch you want to edit
	* @param NewCode This is the new code you want to assign to the branch
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void EditBranch(String Name, String NewName, String Code, String NewCode, String Test) throws InterruptedException, IOException {		
		MethodName = "Edit Branch";
		
		//search for the branch you want to edit
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "You tried to edit a non existing staff", MethodName, Test);
		}
		else {
			for (int i = 1; i <= NrOfResults; i++) {
				//open the edit window for the branch with the the name and code you entered
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				CodeCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (BranchCheck.equalsIgnoreCase(Name) && CodeCheck.equalsIgnoreCase(Code)) {
					//click on edit button
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[4]/button[2]")).click();
					Thread.sleep(1000);
					
					//change the name and code of the branch
					//click on save
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(NewName);
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(NewCode);
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
					Thread.sleep(1500);
					
					//search for the branch you just edited
					Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
					Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(NewName);
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
					Thread.sleep(1000);
					
					//check if the branch has been edited
					SecondResults = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
					SecondNrOfResults = Integer.parseInt(SecondResults);
					if (SecondNrOfResults == 0) {
						ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
					}
					else {
						for (int j = 1; j <= SecondNrOfResults; j++) {
							BranchSecondCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ j +"]/td[2]")).getAttribute("innerText");
							CodeSecondCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ j +"]/td[3]")).getAttribute("innerText");
							if (BranchSecondCheck.equalsIgnoreCase(NewName) && CodeSecondCheck.equalsIgnoreCase(NewCode)) {
								ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", MethodName, Test);
							}
							else {
								ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
							}
						}
					}
				}
			}
		}
	}
	
	/** 
	* This method is used to edit Staff
	* @param Name This is the name of the staff you want to edit
	* @param NewName This is the new name you want to assign to the staff
	* @param Branch This is the branch to which the staff is assigned to
	* @param NewBranch This is the new branch you want to assign to the staff
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void EditStaff(String Name, String NewName, String Branch, String NewBranch, String Test) throws InterruptedException, IOException {	
		MethodName = "Edit Staff";
		
		//search for staff you want to edit
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "You tried to edit a non existing staff", MethodName, Test);
		}
		else {
			for (int i = 1; i <= NrOfResults; i++) {
				//search for the staff with the desired name and branch
				StaffCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (StaffCheck.equalsIgnoreCase(Name) && BranchCheck.equalsIgnoreCase(Branch)) {
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[4]/button[2]")).click();
					Thread.sleep(1000);
					
					//change the staff data
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(NewName);
					Select SelectBranch = new Select(Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")));		
					//open branch list
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/select")).click();
					Thread.sleep(500);		
					//select desired branch
					SelectBranch.selectByVisibleText(NewBranch);
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
					Thread.sleep(1500);
					
					//search for edited staff
					Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
					Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(NewName);
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
					Thread.sleep(1000);
					
					//check that the data of the staff have been changed
					SecondResults = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
					SecondNrOfResults = Integer.parseInt(Results);
					if (SecondNrOfResults == 0) {
						ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
					}
					else {
						for (int j = 1; j <= SecondNrOfResults; j++) {
							StaffSecondCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ j +"]/td[2]")).getAttribute("innerText");
							BranchSecondCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ j +"]/td[3]")).getAttribute("innerText");
							if (StaffSecondCheck.equalsIgnoreCase(NewName) && BranchSecondCheck.equalsIgnoreCase(NewBranch)) {
								ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", MethodName, Test);
							}
							else {
								ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
							}
						}
					}
				}
			}
		}
	}

	/** 
	* This method is used to delete Branch
	* @param Name This is the name of the branch
	* @param Code This is the code of the branch
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void DeteleBranch(String Name, String Code, String Test) throws InterruptedException, IOException {
		MethodName = "Delete Branch";
		
		//search for branch to delete
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(Name);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		//check that a branch with that name exists
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		Decrement = NrOfResults;
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "There is no branch to delete with name "+ Name +" and code "+ Code, MethodName, Test);
		}
		else if (NrOfResults >= 1) {
			for (int i = 1; i <= NrOfResults; i++) {				
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ Decrement +"]/td[2]")).getAttribute("innerText");
				CodeCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ Decrement +"]/td[3]")).getAttribute("innerText");
				if (BranchCheck.equalsIgnoreCase(Name) && CodeCheck.equalsIgnoreCase(Code)) {
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ Decrement +"]/td[4]/button[3]")).click();
					Thread.sleep(500);
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
					Thread.sleep(1000);
					
					//check that branch has been deleted
					Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).getAttribute("disabled");
					if (Status == null) {		
						ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Deleted branch with name " + Name + " and code " + Code, MethodName, Test);
					}
					else {
						ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Can't delete branch with staff assigned", MethodName, Test);
						Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[1]")).click();
						Thread.sleep(1000);
					}
					
				}
				Decrement --;
			}
		}
	}
	
	/** 
	* This method is used to delete Staff
	* @param Name This is the name of the staff
	* @param Branch This is the branch to which the staff is assigned to
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void DeleteStaff(String Name, String Branch, String Test) throws InterruptedException, IOException {		
		MethodName = "Delete Staff";
		
		//check that there is a staff with this name
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		Decrement = NrOfResults;
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "There is no staff to delete with name "+ Name +" and branch "+ Branch, MethodName, Test);
		}
		else {
			for (int i = 1; i <= NrOfResults; i++) {
				//search for the staff that is going to be deleted
				StaffCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (StaffCheck.equalsIgnoreCase(Name) && BranchCheck.equalsIgnoreCase(Branch)) {
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[4]/button[3]")).click();
					Thread.sleep(500);
					Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
					Thread.sleep(500);
					DeletedStaffs ++;
					NrOfResults --;
					i = 1;
				}
			}
		}
		
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed. Delete "+ DeletedStaffs +" staffs with name "+ Name +" and assigned branch "+ Branch, MethodName, Test);
		}
		else {
			for (int i = 1; i <= NrOfResults; i++) {
				//search for the staff that is going to be deleted
				StaffCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (StaffCheck.equalsIgnoreCase(Name) && BranchCheck.equalsIgnoreCase(Branch)) {
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Staff has not been deleted", MethodName, Test);
				}
				else if (DeletedStaffs > 0){
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed. Delete "+ DeletedStaffs +" staffs with name "+ Name +" and assigned branch "+ Branch, MethodName, Test);
					i = NrOfResults;
				}
				else if (DeletedStaffs == 0){
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "There is no staff to delete with name "+ Name +" and branch "+ Branch, MethodName, Test);
					i = NrOfResults;
				}
			}
		}
	}
	
	/** 
	* This method is used to query Branch
	* @param SearchCriteria This is the search information
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void QueryBranch(String SearchCriteria, String Test) throws InterruptedException, IOException {
		MethodName = "Query Branch";
		
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(SearchCriteria);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "There is no branch with this information "+ SearchCriteria, MethodName, Test);
		}
		else if (NrOfResults > 0) {
			for (int i = 1; i <= NrOfResults; i++) {
				IDCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[1]/a")).getAttribute("innerText");
				BranchCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[2]")).getAttribute("innerText");
				CodeCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ i +"]/td[3]")).getAttribute("innerText");
				if (IDCheck.equalsIgnoreCase(SearchCriteria) || BranchCheck.equalsIgnoreCase(SearchCriteria) || CodeCheck.equalsIgnoreCase(SearchCriteria)) {
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Found branch with the folowing information: ID "+ IDCheck +" Name "+ BranchCheck +" Code "+ CodeCheck, MethodName, Test);
				}						
			}
		}
	}
	
	/** 
	* This method is used to query Staff
	* @param SearchCriteria This is the search information
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void QueryStaff(String SearchCriteria, String Test) throws InterruptedException, IOException {
		MethodName = "Query Staff";
		
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).clear();
		Mozila.findElement(By.xpath("//*[@id=\"searchQuery\"]")).sendKeys(SearchCriteria);
		Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[2]/form/button")).click();
		Thread.sleep(1000);
		
		Results = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody")).getAttribute("childElementCount");
		NrOfResults = Integer.parseInt(Results);
		if (NrOfResults == 0) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "There is no staff with this information "+ SearchCriteria, MethodName, Test);
		}
		else if (NrOfResults >= 1) {
			for (int i = 1; i <= NrOfResults; i++) {
				IDCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr[" + i + "]/td[1]/a")).getAttribute("innerText");
				StaffCheck = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr[" + i + "]/td[2]")).getAttribute("innerText");
				if (IDCheck.equalsIgnoreCase(SearchCriteria) || StaffCheck.equalsIgnoreCase(SearchCriteria)) {
					ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Found staff with the folowing information: ID "+ IDCheck +" Name "+ BranchCheck, MethodName, Test);
				}						
			}
		}	
	}	
	
	/** 
	* This method is used to logout
	* @param Test This is the name of the test case
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	* @exception IOException On input error
	*/
	public void Logout(String Test) throws InterruptedException, IOException {
		MethodName = "Logout";
		
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/a/span/span[2]")).click();
		Mozila.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/ul/li[4]/a/span[2]")).click();
		Thread.sleep(1500);
		Status = Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/h1")).getAttribute("innerText");												
		if (Status.equalsIgnoreCase("Welcome to Gurukula!")) {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Passed", MethodName, Test);
		}
		else {
			ExcelAction.WriteResults(Tests.Constants.ExcelLocation, "Failed", MethodName, Test);
		}
	}
	
	/** 
	* This method is used to close browser
	* @exception InterruptedException When a thread is waiting, sleeping, or otherwise occupied, 
	* and the thread is interrupted, either before or during the activity
	*/
	public void Close() throws InterruptedException {
		Mozila.close();
		Thread.sleep(1500);
	}
}