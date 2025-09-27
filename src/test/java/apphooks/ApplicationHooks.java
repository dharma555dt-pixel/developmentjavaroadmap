package apphooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;

import java.net.MalformedURLException;
import java.util.Properties;

public class ApplicationHooks {
    private WebDriver driver;
    private Properties properties;

    /*@Before(order = 0)//@Before(value = "@skip_scenario",order = 0) we can write in this way also
    public void skipScenarios(Scenario scenario) {
        System.out.println("SKIPPED SCENARIOS is : "+scenario.getName());
        Assume.assumeTrue(false);
    }*/

    @Before(order = 0)
    public void getProperties() {
        ConfigReader reader = new ConfigReader();
        properties = reader.init_properties();
    }
    @Before(order = 1)
    public void getFactory() throws MalformedURLException {
        DriverFactory factory = new DriverFactory();
        driver=factory.init_driver(properties.getProperty("browser"),  properties.getProperty("RUN_IN_DOCKER"),  properties.getProperty("REMOTE_URL"));

    }
    @After(order = 0)
    public void quitBrowser(){
        driver.quit();
    }
    @After(order = 1)
    public void clickPicture(Scenario sc){
        if (sc.isFailed()){
            String screenShotName = sc.getName().replaceAll("","");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            sc.attach(sourcePath,"image/png",screenShotName);
        }
    }
}
