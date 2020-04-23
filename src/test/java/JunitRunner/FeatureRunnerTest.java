package JunitRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;

import java.io.File;


@RunWith(Cucumber.class)

@CucumberOptions(features = { "src/test/resources/Regression.feature" },
        glue = "StepDefinition",
     plugin = {"pretty", "html:target/target/cucumber-reports","com.cucumber.listener.ExtentCucumberFormatter:cucumber-reports/index.html"},
        monochrome = true
        )

public class FeatureRunnerTest {

  @AfterClass
  public static void writeExtentReport() {
           Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
  }
}
