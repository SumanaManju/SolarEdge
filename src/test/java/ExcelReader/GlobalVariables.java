package ExcelReader;

import org.openqa.selenium.WebDriver;

public class GlobalVariables {

	public static String inputFilePath=System.getProperty("user.dir")+"/TestData/TestData.xls";
	public static String testCaseSheetName="Testcase Data";
	public static String objectspath=System.getProperty("user.dir")+"/src/test/java/ObjectRepo/objects.properties";
	public static String Testcase="";
	public static final String testcasename="TestCaseName";
	public static String testcasenameOnRun="";
	public static final String FFdriverName="webdriver.geckodriver";
	public static final String FFdriverAddress=System.getProperty("user.dir")+"/Drivers/geckodriver.exe";
	public static WebDriver webdriver;
	public static String comments="";
	public static final String browser="Browser";
	public static int localRow=0;
}
