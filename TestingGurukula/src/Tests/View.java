package Tests;

import java.io.IOException;
import org.openqa.selenium.By;

public class View {

	ReusableAcctions Action = new ReusableAcctions();
	String Branch = "";
	String Code = "";
	String Staff = "";
	String PageText = "";

	//checks that a branch information can be seen
	public void ViewBranch() throws InterruptedException, IOException {
		Action.OpenBrowser();
		Action.Login(Tests.Constants.User, Tests.Constants.Password);
		Action.NavigateToBranch();
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[1]")).click();
		Thread.sleep(1000);
		
		PageText = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/span")).getAttribute("innerText");
		Branch = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[1]/td[2]/input")).getAttribute("defaultValue");
		Code = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[2]/td[2]/input")).getAttribute("defaultValue");
		if (PageText.equalsIgnoreCase("Branch") && 
			(Branch.equalsIgnoreCase(Tests.Constants.Branch)) && 
			Code.equalsIgnoreCase(Tests.Constants.Code)) {
			Action.WriteResults(Tests.Constants.ExcelLocation,7,1,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,7,1,"Failed");
		}
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/button")).click();
	}
	
	//checks that a staff information can be seen
	public void ViewStaff() throws InterruptedException, IOException {
		Action.NavigateToStaff();
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[1]")).click();
		Thread.sleep(1000);
		
		PageText = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/h2/span")).getAttribute("innerText");
		Staff = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[1]/td[2]/input")).getAttribute("defaultValue");
		Branch = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/table/tbody/tr[2]/td[2]/input")).getAttribute("defaultValue");
		if (PageText.equalsIgnoreCase("Staff") && 
			Staff.equalsIgnoreCase(Tests.Constants.Staff) && 
			Branch.equalsIgnoreCase(Tests.Constants.Branch)) {
			Action.WriteResults(Tests.Constants.ExcelLocation,7,2,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,7,2,"Failed");
		}
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/button")).click();
		Action.Logout(7,3);
		Action.Close();
	}
}
