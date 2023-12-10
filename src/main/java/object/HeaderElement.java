package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderElement {

    private final WebDriver driver;

    @FindBy(id = "nav-link-login")
    private WebElement loginLink;

    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;


    @FindBy(id = "nav-link-new-post")
    private WebElement newPostButton;

    @FindBy(id = "homeIcon")
    private WebElement siteLogo;

    public HeaderElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {
        loginLink.click();
    }

    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();
    };

    public boolean isProfileLinkPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOf(profileLink)).isDisplayed();
    };

    public boolean isLogoPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOf(siteLogo)).isDisplayed();
    }

    public void clickNewPost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(newPostButton)).click();
    }
}
