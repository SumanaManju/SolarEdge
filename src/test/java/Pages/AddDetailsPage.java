package Pages;

import ExcelReader.ExcelOperations;
import ExcelReader.GlobalVariables;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDetailsPage {
    ExcelOperations EO = new ExcelOperations();

    public void addDetails() throws Throwable {
        // Changing language to English(US)
        GlobalVariables.webdriver.findElement(By.className("fancy-select")).click();
        Thread.sleep(5000);
        GlobalVariables.webdriver.findElement(By.xpath("//li[contains(text(), 'English (US)')]")).click();
        Thread.sleep(3000);

        // Fill in the form
        EO.EntertextboxValue("SE_AD_YourName", "YourName");
        EO.EntertextboxValue("SE_AD_YourEmailAddress", "YourEmailAddress");
        EO.EntertextboxValue("SE_AD_Topic", "Topic");
        EO.EntertextboxValue("SE_AD_Message", "Message");
        Thread.sleep(3000);

        // Selecting Country from dropdown
        WebElement country = GlobalVariables.webdriver.findElement(By.id("edit-field-client-address-0-address-country-code--2"));
        EO.selectByDropDownValue(country, "Country");

        EO.EntertextboxValue("SE_AD_FirstName", "FirstName");
        EO.EntertextboxValue("SE_AD_LastName", "LastName");
        EO.EntertextboxValue("SE_AD_StreetAddress", "StreetAddress");
        EO.EntertextboxValue("SE_AD_City", "City");
        Thread.sleep(3000);

        // Selecting State from dropdown
        WebElement state = GlobalVariables.webdriver.findElement(By.name("field_client_address[0][address][administrative_area]"));
        EO.selectByDropDownValue(state, "State");

        EO.EntertextboxValue("SE_AD_ZipCode", "ZipCode");
        EO.EntertextboxValue("SE_AD_Phone", "Phone");

        // Entering DATE & TIME
        EO.EnterDateTimeValue("SE_AD_StartDate", "StartDate");
        EO.EnterDateTimeValue("SE_AD_StartTime", "StartTime");
        EO.EnterDateTimeValue("SE_AD_EndDate", "EndDate");
        EO.EnterDateTimeValue("SE_AD_EndTime", "EndTime");
        Thread.sleep(2000);
    }

    public void validateSuccessmessage() throws Throwable {
        String msg = EO.GetTextboxValue("SE_AD_SubmitMessage");
        Assert.assertTrue("Success Message is " + msg, msg.contains("Successfully submitted"));
    }

    public void submitForm() throws Throwable {
        EO.ButtonClick("SE_AD_SendMessageBtn");
    }

    public void calculateNumber() throws Throwable {
        int number = 0, additionOfTwoNumbwers = 0;
        String label = GlobalVariables.webdriver.findElement(By.id("edit-field-number-wrapper")).getText();
        //label="How much is 4 plus 1";
        System.out.println("Label is:" + label);
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(label);
        while (m.find()) {
            number = Integer.parseInt(m.group());
            System.out.println(number);
            additionOfTwoNumbwers = additionOfTwoNumbwers + number;
        }
        System.out.println("The addition of two numbers:" + additionOfTwoNumbwers);
        GlobalVariables.webdriver.findElement(By.name("field_number[0][value]")).sendKeys(new String[]{String.valueOf(additionOfTwoNumbwers)});
        Thread.sleep(5000);
    }

}
