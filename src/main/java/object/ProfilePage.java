package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;
    @FindBy(css = ".profile-user-settings h2")
    private WebElement userProfileName;

    @FindBy(xpath = "//div[contains(@class, \"profile-stats\")]//li[position()=1]")
    private WebElement numberOfPosts;

    @FindBy(css = "strong.profile-stat-count")
    private WebElement numberOfPostsInt;
    @FindBy(id = "followers")
    private WebElement numberOfFollowers;

    @FindBy(id = "following")
    private WebElement numberOfFollowing;

    private static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/";

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }

    public String getUserProfileName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOf(userProfileName)).getText();
    }

    public String printNumberOfPosts() {
        return numberOfPosts.getText();
    }

    public int getNumberOfPostsCount() {
        int num = Integer.parseInt(numberOfPostsInt.getText());
        return num;
    }

    public String getNumberOfFollowers() {
        return numberOfFollowers.getText();
    }

    public String getNumberOfFollowing() {
        return numberOfFollowing.getText();
    }

}
