package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import utils.Constants;
import java.time.Duration;


public class AdminSteps {
    WebDriver driver = new ChromeDriver();
    LoginPage loginPage = new LoginPage(driver);
    // Background Step Definitions

    @Given("User is on the HRM login page")
    public void userIsOnLoginPage() {
        driver.get(Constants.URL);
    }

    @When("User enters valid credentials")
    public void userEntersValidCredentials() {
        WebElement username = driver.findElement(By.id("txtUsername"));
        WebElement password = driver.findElement(By.id("txtPassword"));
        username.sendKeys("Admin");
        password.sendKeys("Hum@nhrm123");

        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        loginButton.click();
    }

    @Then("User should be logged in successfully")
    public void userShouldBeLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement dashboardLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_dashboard_index")));
        assert dashboardLink.isDisplayed();
    }

    // Scenario 1: Admin adds an employee without an employee ID

    @Given("The admin is on the Add Employee page")
    public void adminIsOnAddEmployeePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement pimMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_pim_viewPimModule")));
        pimMenu.click();

        WebElement addEmployeeMenu = driver.findElement(By.id("menu_pim_addEmployee"));
        addEmployeeMenu.click();
    }

    @When("The admin enters valid details without an employee ID")
    public void adminEntersDetailsWithoutEmployeeID() {
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement middleName = driver.findElement(By.id("middleName"));
        WebElement lastName = driver.findElement(By.id("lastName"));

        firstName.sendKeys("John");
        middleName.sendKeys("M");
        lastName.sendKeys("Doe");
    }

    @Then("The system generates a unique employee ID")
    public void systemGeneratesEmployeeID() {
        WebElement employeeIdField = driver.findElement(By.id("employeeId"));
        assert !employeeIdField.getAttribute("value").isEmpty();
    }

    @Then("The employee is successfully added")
    public void employeeIsSuccessfullyAdded() {
        WebElement saveButton = driver.findElement(By.id("btnSave"));
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement personalDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pdMainContainer']/div[1]/h1")));
        assert personalDetails.isDisplayed();
    }

    // Scenario 2: Admin adds an employee with a unique employee ID

    @When("The admin enters valid details with an employee ID")
    public void adminEntersDetailsWithEmployeeID() {
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement middleName = driver.findElement(By.id("middleName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement employeeId = driver.findElement(By.id("employeeId"));

        firstName.sendKeys("Jane");
        middleName.sendKeys("A");
        lastName.sendKeys("Smith");
        employeeId.sendKeys("123456");
    }

    @Then("The employee is successfully added with the provided employee ID")
    public void employeeIsAddedWithProvidedID() {
        WebElement saveButton = driver.findElement(By.id("btnSave"));
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement personalDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pdMainContainer']/div[1]/h1")));
        assert personalDetails.isDisplayed();
    }

    // Scenario 3: Admin submits incomplete or invalid employee details

    @When("The admin enters invalid or incomplete details")
    public void adminEntersInvalidDetails() {
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));

        firstName.sendKeys("");
        lastName.sendKeys("");
    }

    @Then("The system shows appropriate error messages near the input fields")
    public void systemShowsErrorMessages() {
        WebElement saveButton = driver.findElement(By.id("btnSave"));
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement firstNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@for='firstName' and contains(text(),'Required')]")));
        assert firstNameError.isDisplayed();  // Verifying the error message appears for first name

        WebElement lastNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@for='lastName' and contains(text(),'Required')]")));
        assert lastNameError.isDisplayed();  // Verifying error for last name if needed
    }
}

