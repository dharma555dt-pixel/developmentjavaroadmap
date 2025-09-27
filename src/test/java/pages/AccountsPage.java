package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ElementUtilities;

import java.util.List;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtilities elementUtils;

    @FindBy(css = "div#center_column span")
    private List<WebElement> count;


    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtilities(driver);
    }

    private WebElement getDynamicElement(String value) {
        String xpath = "//span[text()='" + value + "']";
        return elementUtils.findElement(By.xpath(xpath));

    }

    public boolean checkIconIsVisible(String value) {
        return elementUtils.isElementDisplayed(getDynamicElement(value));
    }

    public int getSizeOfIcons() {
        return count.size() - 1;
    }
    public String getAccountsPageTitlePage(){
        return elementUtils.getTitleWithJS();

    }
}
