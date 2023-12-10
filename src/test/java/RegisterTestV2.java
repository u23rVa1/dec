import object.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegisterTestV2 extends Helper {

    /**
     * Only difference with first test version
     * is the use of Data provider
     */

    @DataProvider(name = "fillPassword")
    public Object[][] fillPassword() {
        return new Object[][]{
                {"Test122!"}
        };
    }


    @Test(dataProvider = "fillPassword")
    public void testRegisterNewUser(String pass) {
        // Open Home page
        HomePage homePage = new HomePage(super.getDriver());
        homePage.gotoPage();
        homePage.isPageLoaded();

        // Click Login link in header
        HeaderElement header = new HeaderElement(super.getDriver());
        header.clickLogin();

        // Click Register link
        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.isPageLoaded();
        loginPage.checkPresenceOfSignInForm();
        loginPage.clickRegisterButton();

        // Verify page and form are loaded
        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.gotoPage();
        registerPage.isPageLoaded();
        registerPage.isFormLoaded();

        // Fill username
        String userName = generateString(4, 9);
        registerPage.populateUsername(userName);

        // Fill email
        String email = generateEmail(2, 10);
        registerPage.populateEmail(email);

        // Fill password and confirm password fields
        registerPage.populatePassword(pass);
        registerPage.populateConfirmPassword(pass);

        // Click Sign in button
        registerPage.clickSignInButton();

        // Verify success toast message
        Assert.assertEquals(registerPage.getRegistrationToastMessage(), "Successful register!");

        // Verify redirect to home page
        homePage.isPageLoaded();

        // Go to Profile page
        header.clickProfile();
        ProfilePage profilePage = new ProfilePage(getDriver());
        profilePage.isPageLoaded();

        // Verify Profile name matches the username of the new user
        Assert.assertEquals(profilePage.getUserProfileName(), userName, "Profile name does not match!");
    }
}
