package utilities;



import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtilities {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Constructor
    public ElementUtilities(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    /* ================== BASIC ACTIONS ================== */

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void typeText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
    }

    public String getElementText(WebElement element) {
        try {
            // Initialize wait if it's null
            if (wait == null) {
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            }

            // Wait for element to be visible and get text
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
            String text = visibleElement.getText().trim();
            System.out.println("Element text retrieved: " + text);
            return text;

        } catch (Exception e) {
            System.out.println("Error getting element text: " + e.getMessage());
            throw new RuntimeException("Could not get text from element: " + e.getMessage());
        }
    }
    public boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public String getElementAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /* ================== DROPDOWNS ================== */

    public void selectByVisibleText(WebElement element, String text) {
        new Select(wait.until(ExpectedConditions.visibilityOf(element))).selectByVisibleText(text);
    }

    public void selectByValue(WebElement element, String value) {
        new Select(wait.until(ExpectedConditions.visibilityOf(element))).selectByValue(value);
    }

    public void selectByIndex(WebElement element, int index) {
        new Select(wait.until(ExpectedConditions.visibilityOf(element))).selectByIndex(index);
    }

    public List<WebElement> getAllOptions(WebElement element) {
        return new Select(element).getOptions();
    }

    /* ================== MOUSE & KEYBOARD ACTIONS ================== */

    public void moveToElement(WebElement element) {
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOf(element))).perform();
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(wait.until(ExpectedConditions.visibilityOf(element))).perform();
    }

    public void rightClick(WebElement element) {
        actions.contextClick(wait.until(ExpectedConditions.visibilityOf(element))).perform();
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    public void pressKey(Keys key) {
        actions.sendKeys(key).perform();
    }

    /* ================== ALERTS ================== */

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void sendKeysToAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent()).sendKeys(text);
    }

    /* ================== FRAMES & WINDOWS ================== */

    public void switchToFrame(WebElement frameElement) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    /* ================== JS EXECUTOR ================== */

    public void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void setValueWithJS(WebElement element, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + value + "';", element);
    }

    public String getTitleWithJS() {
        return ((JavascriptExecutor) driver).executeScript("return document.title;").toString();
    }

    /* ================== WAIT HELPERS ================== */

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    public void waitForUrlContains(String fraction) {
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    /* ================== EXTRA ================== */

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    // Basic sendKeys
    public void doSendKeys(WebElement element, String value) {
        try {
            // Add null and empty check
            if (value == null) {
                throw new IllegalArgumentException("Value cannot be null for sendKeys");
            }

            // Wait for element to be visible and enabled
            waitForElementToBeVisible(element);
            waitForElementToBeClickable(element);

            // Clear the field first (uncomment if needed)
            element.clear();

            // Send keys with validation
            if (!value.trim().isEmpty()) {
                element.sendKeys(value);
                System.out.println("Entered value: " + value);
            } else {
                System.out.println("Warning: Empty value provided for sendKeys");
            }

        } catch (Exception e) {
            System.out.println("Error in doSendKeys: " + e.getMessage());
            throw e;
        }
    }

    public void waitForElementToBeVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new RuntimeException("Element not visible: " + e.getMessage());
        }
    }

    public void waitForElementToBeClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            throw new RuntimeException("Element not clickable: " + e.getMessage());
        }
    }

    // SendKeys with explicit wait
    public void doSendKeys(By locator, String value, int timeOut) {
        WebElement element = waitForElementVisible(locator, timeOut);
        element.clear();
        element.sendKeys(value);
    }

    // SendKeys + Enter (useful for search boxes, forms)
    public void doSendKeysAndEnter(WebElement element, String value) {
        element.clear();
        element.sendKeys(value, Keys.ENTER);
    }
    // Wait for element to be visible
    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
