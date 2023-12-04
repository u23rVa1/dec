import object.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class HomeTest extends Base {

    @Test
    public void testHomePage() {
        HomePage homePage = new HomePage(getDriver());

        homePage.gotoPage();

        homePage.isPageLoaded();
    }

}
