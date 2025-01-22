package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class AddEmployeePage {

    WebDriver driver;

    @FindBy(id = "firstName")
    WebElement firstNameField;

    @FindBy(id = "lastName")
    WebElement lastNameField;

    @FindBy(id = "employeeId")
    WebElement employeeIdField;

    @FindBy(id = "btnSave")
    WebElement saveButton;

    @FindBy(id = "menu_pim_addEmployee")
    WebElement addEmployeeMenu;

    @FindBy(css = ".validation-error")
    WebElement validationErrorMessage;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToAddEmployeePage() {
        addEmployeeMenu.click();
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterEmployeeId(String employeeId) {
        employeeIdField.sendKeys(employeeId);
    }

    public void clickSave() {
        saveButton.click();
    }

    public String getValidationErrorMessage() {
        return validationErrorMessage.getText();
    }
}

