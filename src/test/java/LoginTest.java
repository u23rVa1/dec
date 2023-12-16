import object.HeaderElement;
import object.HomePage;
import object.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class LoginTest extends Helper {
    @DataProvider(name = "fillUserCredentials")
    public Object[][] fillUserCredentials() {
        return new Object[][] {
                {"valg", "Test123!"},
                {"test@mails.com", "Test123!"}
        };
    }

    @Test(dataProvider = "fillUserCredentials")
    public void testLoginPage(String username, String password) {
        // Init page instance with driver
        LoginPage loginPage = new LoginPage(super.getDriver());

        // Go to page
        loginPage.gotoPage();

        // Verify page is loaded
        loginPage.isPageLoaded();

        // Verify Presence Of Sign In Form
        loginPage.checkPresenceOfSignInForm();

        // Populate Username Or Email field
        loginPage.fillUsernameOrEmail(username);

        // Populate Password field
        loginPage.fillPassword(password);

        // Click Sign in button
        loginPage.clickSignInButton();

        // Verify 'Successful login' toast message is shown
        Assert.assertEquals(loginPage.getToastMessageText(), "Successful login!");

        // Validate correct page loaded after login
        HomePage homePage = new HomePage(getDriver());
        homePage.isPageLoaded();

        // Verify presence of Profile link
        HeaderElement header = new HeaderElement(getDriver());
        Assert.assertTrue(header.isProfileLinkPresent());
    }
}
