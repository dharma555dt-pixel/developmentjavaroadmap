package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;

import java.util.Map;

public class LoginSteps {
    private String titlePage;
    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @Given("user is on login page")
    public void user_is_on_login_page() {
        DriverFactory.getDriver().get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
    }

    @When("user gets the title of the page")
    public void user_gets_the_title_of_the_page() {
        titlePage = loginPage.getTitlePage();
        System.out.println("title of the login page " + titlePage);
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String expectedTitle) {

        Assert.assertEquals(titlePage, expectedTitle);
    }

    @Then("forget your password link should be displayed")
    public void forget_your_password_link_should_be_displayed() {
        Assert.assertTrue(loginPage.checkFiled());
    }

    @When("user enter username {string}")
    public void user_enter_username(String userName) {
       loginPage.enterUsername(userName);
    }

    @When("enter password {string}")
    public void enter_password(String pwd) {
        loginPage.enterPassword(pwd);

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
       loginPage.clickLogin();
    }



}
