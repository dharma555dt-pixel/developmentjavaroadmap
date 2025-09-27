package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class ParabankLogin {
    private String titlePage;
    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @Given("open parabank url register page")
    public void parabankLoginPage() throws InterruptedException {

        DriverFactory.getDriver().get("https://parabank.parasoft.com/parabank/register.html");
        Thread.sleep(50000);
    }


}
