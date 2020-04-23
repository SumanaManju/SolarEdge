package ExcelReader;
import java.io.*;
import java.util.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExcelOperations {

	public static Workbook excelworkbook;
	public static Sheet sheet;
	getObjectValue OV = new getObjectValue();
	public static WritableWorkbook excelworkbook1;
	public static Sheet shSheet;
	public static WritableSheet shSheet1;
	public static Sheet sheetReaderMethod(String sheetname,String filename) {

		try {
			excelworkbook = Workbook.getWorkbook(new File(filename));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = excelworkbook.getSheet(sheetname);
		return sheet;
	}

	private int getKeywordRowNum(String keyword) {
		Sheet sheet = sheetReaderMethod(GlobalVariables.testCaseSheetName,GlobalVariables.inputFilePath);
		int rowCountTotal = sheet.getRows();
		int localRowNum = 0;
		for (int local = 1; local < rowCountTotal; local++) {
			if (keyword.equals(sheet.getCell(0, local).getContents())) {
				localRowNum = local;
				break;
			}

		}
		return localRowNum;
	}

	public int getKeywordRowNum1(String keyword, String sheetname) {
		Sheet sheet = sheetReaderMethod(sheetname,GlobalVariables.inputFilePath);
		int rowCountTotal = sheet.getRows();
		int localRowNum = 0;
		for (int local = 1; local < rowCountTotal; local++) {
			if (keyword.equals(sheet.getCell(0, local).getContents())) {
				localRowNum = local;
				break;
			}

		}
				return localRowNum;
	}

	public int getKeywordColNum(String keyword) {
		Sheet sheet = sheetReaderMethod(GlobalVariables.testCaseSheetName,GlobalVariables.inputFilePath);
		int colCountTotal = sheet.getColumns();
		int localColNum = 0;
		for (int local = 0; local < colCountTotal; local++) {

			if (keyword.equals(sheet.getCell(local, 0).getContents())) {
				localColNum = local;
				break;
			}
		}
		return localColNum;

	}

	public String getTestCaseColumnData(String testcasename, String keyword) {
		Sheet sheet = sheetReaderMethod(GlobalVariables.testCaseSheetName,GlobalVariables.inputFilePath);
		int testcaseRowNum = getKeywordRowNum(testcasename);
		int keywordColNum = getKeywordColNum(keyword);
		String testcaseKeywordContent = sheet.getCell(keywordColNum, testcaseRowNum).getContents().trim();
		return testcaseKeywordContent;
	}

	public void webdriverLaunch(String arg1) throws InterruptedException {
		String browserName=getTestCaseColumnData(GlobalVariables.Testcase, "BrowserName");
		switch(browserName)
		{
			case "GoogleChrome":
				WebDriverManager.chromedriver().version("2.40").setup();
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--disable-infobars");
				//chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("disable-gpu");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.setExperimentalOption("useAutomationExtension", false);
				chromeOptions.addArguments("window-size=1920x1080");
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				GlobalVariables.webdriver=new ChromeDriver(capabilities);
				Thread.sleep(5000);
				//GlobalVariables.webdriver.manage().window().maximize();
				Thread.sleep(2000);
				System.out.println("URL is:"+arg1);
				GlobalVariables.webdriver.get(arg1);
				Thread.sleep(2000);
				break;

			case "InternetExplorer":
				WebDriverManager.iedriver().setup();
				GlobalVariables.webdriver=new InternetExplorerDriver();
				GlobalVariables.webdriver.manage().window().maximize();
				Thread.sleep(2000);
				System.out.println("URL is:"+arg1);
				GlobalVariables.webdriver.get(arg1);

				break;

			case "FireFox":
				WebDriverManager.firefoxdriver().setup();
				System.setProperty(GlobalVariables.FFdriverName, GlobalVariables.FFdriverAddress);
				FirefoxOptions options1 = new FirefoxOptions();
				//options1.addArguments("--headless");
				GlobalVariables.webdriver=new FirefoxDriver(options1);
				GlobalVariables.webdriver.manage().window().maximize();
				Thread.sleep(2000);
				System.out.println("URL is:"+arg1);
				GlobalVariables.webdriver.get(arg1);
				break;
		}
	}

	public void EntertextboxValue(String arg1, String arg2) throws Throwable {
		WebDriverWait wait = new WebDriverWait(GlobalVariables.webdriver, 30);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OV.ObjectValue(arg1))));
		element.clear();
		element.click();
		element.sendKeys(getTestCaseColumnData(GlobalVariables.Testcase,arg2));
		Thread.sleep(1000);
	}

	public void EnterDateTimeValue(String arg1, String arg2) throws Throwable {
		WebDriverWait wait = new WebDriverWait(GlobalVariables.webdriver, 30);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OV.ObjectValue(arg1))));
		element.sendKeys(getTestCaseColumnData(GlobalVariables.Testcase,arg2));
		Thread.sleep(2000);
	}

	public void selectByDropDownValue(WebElement ele, String arg2) throws Throwable {
		Select dropdown = new Select(ele);
		dropdown.selectByVisibleText(getTestCaseColumnData(GlobalVariables.Testcase, arg2));
		Thread.sleep(2000);
	}

	public void ButtonClick(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(GlobalVariables.webdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OV.ObjectValue(arg1)))).click();
		Thread.sleep(1000);
	}
	public String GetTextboxValue(String arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(GlobalVariables.webdriver, 30);
		Thread.sleep(1000);
		 return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OV.ObjectValue(arg1)))).getText();

	}
}

