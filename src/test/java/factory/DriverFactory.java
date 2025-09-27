package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> local = new ThreadLocal<>();

    public WebDriver init_driver(String browser, String value, String remoteUrl) {
        System.out.println(">>> Requested Browser: " + browser);
        System.out.println(">>> in docker value in string Browser: " + value);
        System.out.println(">>> RAW URL: '" + remoteUrl + "'");

        try {
            // Convert string to boolean for docker parameter
            boolean runInDocker = Boolean.parseBoolean(value);

            System.out.println(">>> RUN_IN_DOCKER: after parsing " + runInDocker);
            System.out.println(">>> REMOTE_URL: " + remoteUrl);

            // Try Docker/Grid first if requested, with fallback to local
            if (runInDocker && remoteUrl != null && !remoteUrl.trim().isEmpty()) {
                try {
                    System.out.println(">>> Attempting to connect to Docker/Grid...");
                    WebDriver remoteDriver = createRemoteDriver(browser, remoteUrl);
                    local.set(remoteDriver);
                    System.out.println(">>> Successfully connected to Docker/Grid");
                } catch (Exception e) {
                    System.err.println(">>> FAILED to connect to Docker/Grid: " + e.getMessage());
                    System.out.println(">>> Falling back to local browser execution...");
                    // Fallback to local browser
                    WebDriver localDriver = createLocalDriver(browser);
                    local.set(localDriver);
                }
            } else {
                // Direct local execution
                System.out.println(">>> Running tests locally...");
                WebDriver localDriver = createLocalDriver(browser);
                local.set(localDriver);
            }

            // Common setup
            WebDriver driver = getDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            System.out.println(">>> Driver initialized successfully for: " + browser);

        } catch (Exception e) {
            System.err.println(">>> CRITICAL: Failed to initialize any driver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize driver: " + e.getMessage());
        }

        return getDriver();
    }

    private WebDriver createRemoteDriver(String browser, String remoteUrl) throws MalformedURLException {
        URL gridUrl = new URL(remoteUrl);

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                return new RemoteWebDriver(gridUrl, chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new RemoteWebDriver(gridUrl, firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                return new RemoteWebDriver(gridUrl, edgeOptions);

            default:
                throw new IllegalArgumentException("Browser not supported in Docker/Grid: " + browser);
        }
    }

    private WebDriver createLocalDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Browser not supported locally: " + browser);
        }
    }

    public static synchronized WebDriver getDriver() {
        return local.get();
    }
}