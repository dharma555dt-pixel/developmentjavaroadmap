package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.AccountsPage;
import pages.LoginPage;

import java.util.List;
import java.util.Map;

public class AccountsPageSteps {
    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private AccountsPage accountsPage ;

    @Given("user has already logged in to the application")
    public void user_has_already_logged_in_to_the_application(DataTable dataTable) {
        String password =null;
        String username =null;
        List<Map<String, String>> tableMaps = dataTable.asMaps(String.class, String.class);
        for (Map<String,String> data : tableMaps){
             password = data.get("password");
             username = data.get("username");
        }
        DriverFactory.getDriver().get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        accountsPage=  loginPage.doLogin(username,password);
    }

    @Given("user is on account page")
    public void user_is_on_account_page() {
        String accountsPageTitlePage = accountsPage.getAccountsPageTitlePage();
        System.out.println("title of the aucont page is : "+accountsPageTitlePage);
    }

    @Then("user get account section")
    public void user_get_account_section(DataTable dataTable) {
        List<String> list = dataTable.asList(String.class);
        for (String value : list){
            //accountsPage.checkIconIsVisible(value);
            Assert.assertTrue(accountsPage.checkIconIsVisible(value));
        }

    }

    @Then("account section count should be {int}")
    public void account_section_count_should_be(int count) {
        Assert.assertEquals(count,accountsPage.getSizeOfIcons());
    }

}
