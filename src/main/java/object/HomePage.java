package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;

public class HomePage {

    private static final String PAGE_URL = "http://training.skillo-bg.com:4200/posts/all";
    private final WebDriver driver;

    @FindBy(css = "app-post-detail")
    private List<WebElement> posts;

    public HomePage(WebDriver driver) {
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

    public boolean arePostsLoaded() {
        if (posts.size() > 1) { // loaded posts before lazy load are 3
            return true;
        } else {
            return false;
        }
    }
}
