package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.Constants;

import static org.junit.Assert.assertEquals;

public class LoginSteps {
    WebDriver driver = new ChromeDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("User is on the HRM login page")
    public void userIsOnLoginPage() {
        driver.get(Constants.URL);
    }

    @When("User enters an empty username")
    public void userEntersEmptyUsername() {
        loginPage.enterUsername("");
        loginPage.enterPassword("incorrectPassword");
        loginPage.clickLoginButton();
    }

    @When("User enters an empty password")
    public void userEntersEmptyPassword() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
    }

    @When("User enters incorrect credentials")
    public void userEntersIncorrectCredentials() {
        loginPage.enterUsername("incorrectUsername");
        loginPage.enterPassword("incorrectPassword");
        loginPage.clickLoginButton();
    }

    @Then("User should see the error message {string}")
    public void userShouldSeeErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        assertEquals("Error message mismatch", expectedMessage, actualMessage);

    }

    //VALID LOGIN
        @When("User enters valid credentials")
        public void userEntersValidCredentials() {
            loginPage.enterUsername("Admin");
            loginPage.enterPassword("Hum@nhrm123");
            loginPage.clickLoginButton();
        }

        @Then("User should be logged in successfully")
        public void userShouldBeLoggedInSuccessfully() {
            // You can verify login by checking the presence of an element that only appears after login,
            // such as a dashboard element or a user profile
            String dashboardUrl = "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/dashboard";
            assertEquals("User is not logged in", dashboardUrl, driver.getCurrentUrl());
        }
    }
