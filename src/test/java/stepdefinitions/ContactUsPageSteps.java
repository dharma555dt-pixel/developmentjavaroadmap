package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.ContactUsPage;
import utilities.ExcelUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ContactUsPageSteps {
private ContactUsPage contactUsPage = new ContactUsPage(DriverFactory.getDriver());
    @Given("user navigate to contact us page")
    public void user_navigate_to_contact_us_page() {
    DriverFactory.getDriver().get("http://www.automationpractice.pl/index.php?controller=contact");

    }

    @When("user fills the form from the given {string} and {int}")
    public void user_fills_the_form_from_the_given_and(String sheetName, int rowNumber) throws IOException {
        ExcelUtils utils = new ExcelUtils("P:/photos/personaldata/album/automation.xlsx","automation");
        utils.loadSheet("P:/photos/personaldata/album/automation.xlsx", "automation");
        List<Map<String, String>> sheetDataAsList = utils.getSheetDataAsList();
        String subjectHeading = sheetDataAsList.get(rowNumber).get("Subject Heading");
        String emailAddress = sheetDataAsList.get(rowNumber).get("Email address");
        //String order = sheetDataAsList.get(rowNumber).get("Order reference");
        String message = sheetDataAsList.get(rowNumber).get("Message");
        contactUsPage.fillContactUsFormPage(subjectHeading,emailAddress,message);

    }

    @When("user clicks on send button")
    public void user_clicks_on_send_button() {
       contactUsPage.clickSubmitButton();
    }

    @Then("it shows successful message {string}")
    public void it_shows_successful_message(String message) {
      String mgs= contactUsPage.getSuccessMessage();
        Assert.assertEquals(message,mgs);
    }


}
