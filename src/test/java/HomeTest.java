import object.HeaderElement;
import object.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends Helper {

    @Test(invocationCount = 5)
    public void testHomePage() {
        // Init page instance with driver
        HomePage homePage = new HomePage(super.getDriver());

        // Open home page
        homePage.gotoPage();

        // Confirm page is loaded
        homePage.isPageLoaded();

        // Verify site logo is displayed in Header
        HeaderElement header = new HeaderElement(super.getDriver());
        Assert.assertTrue(header.isLogoPresent());

        // Verify posts on page are loaded
        Assert.assertTrue(homePage.arePostsLoaded());
    }
}
