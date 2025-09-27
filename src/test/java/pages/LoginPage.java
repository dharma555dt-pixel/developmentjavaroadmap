package pages;


import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ElementUtilities;

public class LoginPage {
    private WebDriver driver;
    private ElementUtilities elementUtils;


    @FindBy(xpath = "//input[@id='email']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@id='passwd']")
    WebElement passwordField;
    @FindBy(xpath = "//button[@id='SubmitLogin']")
    WebElement loginButton;
    @FindBy(xpath = "//a[text()='Forgot your password?']")
    WebElement forgotPassword;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtilities(driver);
    }

    public void enterUsername(String username) {
        elementUtils.typeText(usernameField, username);
    }

    public void enterPassword(String password) {
        elementUtils.typeText(passwordField, password);

    }

    public boolean checkFiled() {
        return elementUtils.isElementDisplayed(loginButton);
    }

    public void clickForgotPassWordLink() {
        elementUtils.clickElement(forgotPassword);
    }

    public void clickLogin() {
        elementUtils.clickElement(loginButton);
    }

    public String getTitlePage() {

        return elementUtils.getTitleWithJS();

    }

    public AccountsPage doLogin(String userName, String password) {
        System.out.println("user name " + userName + " password " + password);
        elementUtils.doSendKeys(usernameField,userName);
        elementUtils.doSendKeys(passwordField,password);
        elementUtils.clickElement(loginButton);
        return new AccountsPage(driver);

    }

}
