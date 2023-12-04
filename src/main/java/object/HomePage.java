package object;

import org.openqa.selenium.WebDriver;

public class HomePage {

    private static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/all";
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }



}
