import object.HeaderElement;
import object.HomePage;
import object.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class LoginFailTest extends Helper {

    /**
     * Test that intentionally fails,
     * for demo of screenshot creation
     */

    @DataProvider(name = "fillUserCredentials")
    public Object[][] fillUserCredentials() {
        return new Object[][] {
                {"valg", "Test999!", "Successful login!"} // invalid password in credentials
        };
    }

    @Test(dataProvider = "fillUserCredentials")
    public void testLoginPage(String username, String password, String successLoginToastText) {
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
        Assert.assertEquals(loginPage.getToastMessageText(), successLoginToastText);
    }
}
