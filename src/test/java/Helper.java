import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Helper {

    private WebDriver driver;

    private static final String SCREENSHOTS_DIR = "src\\main\\resources\\screenshots\\";

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    protected final void setUpManager() throws IOException {
        cleanDirectory(SCREENSHOTS_DIR);
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpDriver() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    };

    @AfterMethod
    protected final void closeDriver(ITestResult testResult) {
        takeScreenshot(testResult);
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    protected String generateString(int fromLength, int toLength) {
        return RandomStringUtils.randomAlphanumeric(fromLength, toLength);
    }

    protected String generateEmail(int fromLength, int toLength) {
        return RandomStringUtils.randomAlphanumeric(fromLength, toLength) + "@gmail.com";
    }

    private void takeScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                System.out.println("Cannot create screenshot: " + e);
            }
        }
    }

    private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        Assert.assertTrue(directory.isDirectory(), "Invalid directory!");

        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory:%s%n", directoryPath);
        }
    }

}
