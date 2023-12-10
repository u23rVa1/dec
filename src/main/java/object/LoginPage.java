package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final String PAGE_URL = "http://training.skillo-bg.com:4200/users/login";
    private final WebDriver driver;

    @FindBy(css = "form p.h4")
    private WebElement signInForm;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameOrEmailField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;
    @FindBy(id = "sign-in-button")
    private WebElement signInButton;
    @FindBy(linkText = "Register")
    private WebElement registerButton;
    @FindBy(xpath = "//div[@role=\"alertdialog\"]")
    private WebElement successToastText;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void gotoPage() {
        driver.get(PAGE_URL);
    }

    public void isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe(PAGE_URL));
    }

    public void checkPresenceOfSignInForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(signInForm));
    }

    public void fillUsernameOrEmail(String username) {
        usernameOrEmailField.sendKeys(username);
    }

    public void fillPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public String getToastMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOf(successToastText)).getText();
    }

    public void quickLogin(String username, String password){
        gotoPage();
        fillUsernameOrEmail(username);
        fillPassword(password);
        clickSignInButton();
    }

    public void clickRegisterButton() {
        registerButton.click();
    }
}
