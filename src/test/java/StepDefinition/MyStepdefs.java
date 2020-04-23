package StepDefinition;
import ExcelReader.*;
import Pages.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyStepdefs {
    ExcelOperations EO = new ExcelOperations();
    getObjectValue OV = new getObjectValue();
    AddDetailsPage AD = new AddDetailsPage();
    String siteurl;

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("URL launched successfully");
    }

    @Given("^I open the application \"(.*?)\" of \"(.*?)\" a$")
    public void i_open_the_application_of_a(String arg1, String arg2) throws Throwable {
        GlobalVariables.Testcase = arg2;
        GlobalVariables.localRow = EO.getKeywordRowNum1(GlobalVariables.Testcase, GlobalVariables.testCaseSheetName);
        System.out.println("\nTest case on run is :" + arg2 + "\n");
        System.out.println("Start TimeStamph :" + new SimpleDateFormat("ddMMMyyyy:HHmm").format(new Date()));
        siteurl = EO.getTestCaseColumnData(arg2, arg1);
        EO.webdriverLaunch(siteurl);
    }

    @When("^I fill in the details$")
    public void iAddDetails() throws Throwable {
        AD.addDetails();
    }

    @And("^I calculate the number and insert right number$")
    public void iCalculateTheNumberAndInsertRightNumber() throws Throwable {
        AD.calculateNumber();
    }

    @And("^I click on submit button$")
    public void iClickOnSubmitButton() throws Throwable {
        AD.submitForm();
    }

    @Then("^I check the success message$")
    public void iCheckTheSuccessMessage() throws Throwable {
        AD.validateSuccessmessage();
    }

    @After(order = 0)
    public void AfterSteps() {
        GlobalVariables.webdriver.quit();
    }
}
