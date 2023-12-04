import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Base {

    private WebDriver driver;

/*    public Base(WebDriver driver) {
        this.driver = driver;
    }*/

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    protected final void setUpManager() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    protected final void setUpDriver() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    };

    @AfterMethod
    protected final void closeDriver() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
