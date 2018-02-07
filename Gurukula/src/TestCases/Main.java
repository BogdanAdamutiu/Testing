package TestCases;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestListenerAdapter;
import com.beust.testng.TestNG;

//main class created so that jar file can be saved
public class Main {

	public static void main(String[] args) {
		// Create object of TestNG Class
		TestNG runner=new TestNG();

		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();

		// Add xml file which you have to execute
		suitefiles.add("C:\\Testare\\Proiecte Java\\Gurukula\\testng.xml");

		// now set xml file for execution
		runner.setTestSuites(suitefiles);

		// finally execute the runner using run method
		runner.run();
		}
}