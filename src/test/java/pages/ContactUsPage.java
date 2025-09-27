package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ElementUtilities;

import java.time.Duration;

public class ContactUsPage {
    private WebDriver driver;
    private ElementUtilities elementUtilities;
    @FindBy(id = "id_contact")
    WebElement subjectHeading;
    @FindBy(id="email")
    WebElement emailText;
    @FindBy(id = "id_order")
    WebElement orderRef;
    @FindBy(id="message")
    WebElement message;
    @FindBy(id = "submitMessage")
    WebElement submit;
    @FindBy(id="div#center_column p")
    WebElement successMessage;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        elementUtilities = new ElementUtilities(driver);
        PageFactory.initElements(driver,this);

    }
    public String getContactUsPageTitle(){
        return elementUtilities.getTitleWithJS();
    }
    public void fillContactUsFormPage(String heading,String email,String message1 ){
        Select select = new Select(subjectHeading);
        select.selectByVisibleText(heading);
        //elementUtilities.selectByVisibleText(subjectHeading,heading);
        //elementUtilities.doSendKeys(subjectHeading,heading);

        elementUtilities.doSendKeys(emailText,email);

        elementUtilities.doSendKeys(message,message1);
    }
    public void clickSubmitButton(){
        elementUtilities.clickElement(submit);
    }
    public String getSuccessMessage(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div#center_column p")));
            return messageElement.getText().trim();
        } catch (Exception e) {
            return "Success message not found: " + e.getMessage();
        }

    }

}
