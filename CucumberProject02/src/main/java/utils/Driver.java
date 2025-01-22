package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Driver {

    private static WebDriver driver;
    private static WebDriverWait wait;

    // Method to initialize WebDriver
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase(); // Default to "chrome" if not specified

            // Initialize the WebDriver based on the browser type
            switch (browser) {
                case "chrome":
                    // Ensure that the path to the chromedriver is set via System property
                    String chromeDriverPath = System.getProperty("webdriver.chrome.driver");
                    if (chromeDriverPath == null || chromeDriverPath.isEmpty()) {
                        throw new IllegalArgumentException("Path to chromedriver is not set.");
                    }
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    // Ensure that the path to the geckodriver is set via System property
                    String firefoxDriverPath = System.getProperty("webdriver.gecko.driver");
                    if (firefoxDriverPath == null || firefoxDriverPath.isEmpty()) {
                        throw new IllegalArgumentException("Path to geckodriver is not set.");
                    }
                    System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Configure WebDriver wait
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }

        return driver;
    }

    // Method to quit WebDriver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // Method to get WebDriverWait instance
    public static WebDriverWait getWait() {
        return wait;
    }
}