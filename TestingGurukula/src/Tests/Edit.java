package Tests;

import java.io.IOException;

import org.openqa.selenium.By;

public class Edit {
	
	ReusableAcctions Action = new ReusableAcctions();
	String Branch ="";
	String Code = "";
	String Staff = "";
	
	//checks that branch(name and code) information can be changed
	public void EditBranch() throws InterruptedException, IOException {
		Action.OpenBrowser();
		Action.Login(Tests.Constants.User, Tests.Constants.Password);
		Action.NavigateToBranch();
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[2]")).click();
		Thread.sleep(1000);
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys("Edit");
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys("7");
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		Branch = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		Code = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (Branch.equalsIgnoreCase(Tests.Constants.Branch + "Edit") && Code.equalsIgnoreCase(Tests.Constants.Code + "7")) {
			Action.WriteResults(Tests.Constants.ExcelLocation,8,1,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,8,1,"Failed");
		}
		Thread.sleep(2000);
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[2]")).click();
		Thread.sleep(1000);
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.NewBranch);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[3]/input")).sendKeys(Tests.Constants.NewCode);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		Branch = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		Code = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (Branch.equalsIgnoreCase(Tests.Constants.NewBranch) && Code.equalsIgnoreCase(Tests.Constants.NewCode)) {
			Action.WriteResults(Tests.Constants.ExcelLocation,8,2,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,8,2,"Failed");
		}
	}
	
	
	//checks that staff information can be changed
	//also checks that branch information changes automatically when branch is edited
	public void EditStaff() throws InterruptedException, IOException {
		Action.NavigateToStaff();
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[2]")).click();
		Thread.sleep(1000);
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys("Edit");
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		Staff = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		Branch = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (Staff.equalsIgnoreCase(Tests.Constants.Staff + "Edit") && Branch.equalsIgnoreCase(Tests.Constants.NewBranch)) {
			Action.WriteResults(Tests.Constants.ExcelLocation,8,3,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,8,3,"Failed");
		}
		Thread.sleep(2000);
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).clear();
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[2]/div[2]/input")).sendKeys(Tests.Constants.NewStaff);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1500);
		
		Staff = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[2]")).getAttribute("innerText");
		Branch = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[3]")).getAttribute("innerText");
		if (Staff.equalsIgnoreCase(Tests.Constants.NewStaff) && Branch.equalsIgnoreCase(Tests.Constants.NewBranch)) {
			Action.WriteResults(Tests.Constants.ExcelLocation,8,4,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,8,4,"Failed");
		}
		Action.Logout(8,5);
		Action.Close();
	}
}
