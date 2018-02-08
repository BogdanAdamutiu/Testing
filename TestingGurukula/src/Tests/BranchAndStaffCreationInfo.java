package Tests;

import java.io.IOException;

import org.openqa.selenium.By;

public class BranchAndStaffCreationInfo {

	ReusableAcctions Action = new ReusableAcctions();
	String Status = "";
	
	//checks data format allowed for branch creation
	public void BranchCreationInfo() throws InterruptedException, IOException {	
		Action.OpenBrowser();
		Action.Login(Tests.Constants.User, Tests.Constants.Password);
		Action.NavigateToBranch();
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1000);
		
		//checks if name of branch is a mandatory field
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[1]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field is required.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,1,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,1,"Failed");
		}
		
		//checks if a short name can be added for a branch
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.Short);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field is required to be at least 5 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,2,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,2,"Failed");
		}
		
		//checks if a long name can be added as a name of branch
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(1000);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.Long);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[3]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field cannot be longer than 20 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,3,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,3,"Failed");
		}
		
		//checks if special characters can be added as a name of branch
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(1000);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.Special);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("innerText");
		Thread.sleep(500);
		if (Status.equalsIgnoreCase("This field should follow pattern ^[a-zA-Z\\\\s]*$")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,4,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,4,"Failed");
		}
				
		//checks if numbers and letters can be added as name of branch
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(1000);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.Combined);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("innerText");
		Thread.sleep(500);
		if (Status.equalsIgnoreCase("This field should follow pattern ^[a-zA-Z\\\\s]*$.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,5,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,5,"Failed");
		}
		
		//checks if upper case characters can be added as name of branch
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(1000);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.Upper);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("hidden");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,6,"Failed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,6,"Passed");
		}
		
		//checks if code field is mandatory
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/div/p[1]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field is required.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,7,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,7,"Failed");
		}
		
		//checks if letters can be added as a code of branch
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(Tests.Constants.Short);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/div/p[4]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field should follow pattern ^[A-Z0-9]*$.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,8,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,8,"Failed");
		}
		
		//checks if special characters can make the code of a branch
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
		Thread.sleep(1000);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(Tests.Constants.Special);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/div/p[4]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field should follow pattern ^[A-Z0-9]*$.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,9,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,9,"Failed");
		}
		
		//checks if a single number can be set as code of a branch
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
		Thread.sleep(1000);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys("1");
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/div/p[2]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field is required to be at least 2 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,10,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,10,"Failed");
		}		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[1]")).click();
		Thread.sleep(1500);
	}
	
	//checks data format allowed for staff creation
	public void StaffCreationInfo() throws InterruptedException, IOException {
		//opens staff page
		Action.NavigateToStaff();	
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/div[1]/button")).click();
		Thread.sleep(1500);
		
		//checks if staff name field is mandatory
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[1]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field is required.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,11,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,11,"Failed");
		}
		Thread.sleep(500);
		
		//checks if numbers and letters can make the name of a staff
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.Combined);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("innerText");
		Thread.sleep(500);
		if (Status.equalsIgnoreCase("This field should follow pattern ^[a-zA-Z\\\\s]*$.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,12,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,12,"Failed");
		}
		Thread.sleep(500);
		
		//checks if special characters are allowed in the name of a staff
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.Special);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[4]")).getAttribute("innerText");
		Thread.sleep(500);
		if (Status.equalsIgnoreCase("This field should follow pattern ^[a-zA-Z\\\\s]*$.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,13,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,13,"Failed");
		}
		Thread.sleep(500);
		
		//checks if a long name can be set as staff name
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.Long);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/div/p[3]")).getAttribute("innerText");
		if (Status.equalsIgnoreCase("This field cannot be longer than 50 characters.")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,14,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,14,"Failed");
		}
		Thread.sleep(500);
		
		//checks that a single letter can be set as name for staff
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys("T");
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).getAttribute("disabled");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,15,"Failed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,5,15,"Passed");
		}
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[1]")).click();
		Thread.sleep(500);
		Action.Logout(5, 16);
		Action.Close();
	}	
}