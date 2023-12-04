package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final String PAGE_URL = "http://training.skillo-bg.com:4300/users/login";
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoPage() {
        driver.get(PAGE_URL);
    }

    public boolean isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL));
    }

    public void checkPresenceOfSignInForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement signInForm = driver.findElement(By.cssSelector("form p.h4"));
        wait.until(ExpectedConditions.visibilityOf(signInForm));
    }

    public void fillUsernameOrEmail(String username) {
        WebElement elem = driver.findElement(By.id("defaultLoginFormUsername"));
        elem.sendKeys(username);
    }

    public void fillPassword(String password) {
        WebElement elem = driver.findElement(By.id("defaultLoginFormPassword"));
        elem.sendKeys(password);
    }

    public void clickSignInButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button"))).click();
    }

/*    public void fillEmailOrUsername() {
        WebElement elem = driver.findElement(By.id("defaultLoginFormUsername"));
        elem.sendKeys("test@test.com");
    }*/


}
