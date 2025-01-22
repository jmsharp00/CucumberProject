package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    // Constructor to initialize the driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locating Web Elements
    private By usernameField = By.name("txtUsername");
    private By passwordField = By.name("txtPassword");
    private By loginButton = By.id("btnLogin");
    private By errorMessage = By.id("spanMessage");

    // Add the dashboard element to verify if user is logged in successfully
    private By dashboardElement = By.id("welcome"); // Replace with the actual locator of the element after successful login

    // Method to enter username
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Method to click the login button
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Method to retrieve error messages
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    // Method to check if the user is logged in
    public boolean isLoggedIn() {
        try {
            // Check if the dashboard element is displayed after login, indicating successful login
            WebElement dashboard = driver.findElement(dashboardElement);
            return dashboard.isDisplayed(); // returns true if element is displayed
        } catch (Exception e) {
            // If the element is not found (login failed), return false
            return false;
        }
    }

    // Method to clear the fields (for retries)
    public void clearFields() {
        driver.findElement(usernameField).clear();
        driver.findElement(passwordField).clear();
    }
}