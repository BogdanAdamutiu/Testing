package Tests;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		ReusableAcctions Test = new ReusableAcctions();
		Test.ResultsFile(Tests.Constants.ExcelLocation);
		Test.OpenBrowser();
		Test.Login(Tests.Constants.User, Tests.Constants.Password);	
		
	}

}
