package Tests;

import java.io.IOException;
import org.openqa.selenium.By;

public class Delete {
	
	DriverReusableActions Action = new DriverReusableActions();
	String Status = "";
	
	public void OpenAndLogin() throws InterruptedException {
		Action.OpenBrowser();
		Action.Login(Tests.Constants.User, Tests.Constants.Password);
	}
	
	//delete branch
	public void DeteleBranch(int Staff) throws InterruptedException, IOException {		
		Action.NavigateToBranch();
		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).click();
		Thread.sleep(500);
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(1000);
		if (Staff == 0) {
			Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).getAttribute("disabled");
			if (Status != null) {
				Action.WriteResults(Tests.Constants.ExcelLocation,9,1,"Failed");
			}
			else {
				Action.WriteResults(Tests.Constants.ExcelLocation,9,1,"Passed");
				Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[1]")).click();
				Thread.sleep(1000);
			}
		}
		else if (Staff == 1) {
			Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).getAttribute("disabled");
			if (Status != null) {
				Action.WriteResults(Tests.Constants.ExcelLocation,9,3,"Passed");
			}
			else {
				Action.WriteResults(Tests.Constants.ExcelLocation,9,3,"Failed");
			}
		}
	}
	
	//delete staff
	public void DeleteStaff() throws InterruptedException, IOException {
		Action.NavigateToStaff();		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).click();
		Thread.sleep(500);		
		Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/form/div[3]/button[2]")).click();
		Thread.sleep(500);
		Status = Action.Mozila.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr/td[4]/button[3]")).getAttribute("disabled");
		if (Status != null) {
			Action.WriteResults(Tests.Constants.ExcelLocation,9,2,"Passed");
		}
		else {
			Action.WriteResults(Tests.Constants.ExcelLocation,9,2,"Failed");
		}
	}
		
	public void LogoutAndClose() throws InterruptedException, IOException {
		Action.Logout(9,4);
		Action.Close();
	}
}
