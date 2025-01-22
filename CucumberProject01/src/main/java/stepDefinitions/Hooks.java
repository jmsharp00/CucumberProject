package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        // Optional: Maximize the window
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");  // Replace with your login page URL
    }

    // @After
    //public void tearDown() {
    // Close the browser after the test
    // if (driver != null) {
    //    driver.quit();
}
