package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final String PAGE_URL = "http://training.skillo-bg.com:4200/users/register";

    public static String PASSWORD = "Test123!";

    private final WebDriver driver;

    @FindBy(css = "form h4")
    private WebElement signUpFormHeader;
    @FindBy(css = "form.form-container")
    private WebElement signUpForm;
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(css = "[type=\"email\"]")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "verify-password")
    private WebElement confirmPasswordField;

    @FindBy(id = "sign-in-button")
    private WebElement signInButton;

    @FindBy(className = "toast-message")
    private WebElement toastMessageRegister;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void gotoPage() {
        driver.get(PAGE_URL);
    }

    public void isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(PAGE_URL));
    }

    public void isFormLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signUpForm));
    }

    public void populateUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void populateEmail(String email) {
        emailField.sendKeys(email);
    }

    public void populatePassword(String password) {
        passwordField.sendKeys(password);
    }

    public void populateConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public void clickSignInButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public String getRegistrationToastMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(toastMessageRegister));
        return toastMessageRegister.getText().trim();
    }

}
