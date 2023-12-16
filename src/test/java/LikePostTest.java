import object.HeaderElement;
import object.HomePage;
import object.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LikePostTest extends Helper {
    @DataProvider(name = "fillUserCredentials")
    public Object[][] fillUserCredentials() {
        return new Object[][]{
                {"valg", "test@mails.com", "Test123!"}
            };
    }

    @Test(dataProvider = "fillUserCredentials")
    public void testLikeAPost(String username, String email, String password) {
        // Login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.quickLogin(username, password);

        // Click and close toast message for Successful login
        loginPage.clickKillToastMessage();

        // Verify presence of Profile link - confirm User is logged in
        HeaderElement header = new HeaderElement(super.getDriver());
        header.isProfileLinkPresent();

        // Verify posts on page are loaded
        HomePage homePage = new HomePage(super.getDriver());
        homePage.isPageLoaded();
        Assert.assertTrue(homePage.arePostsLoaded());

        //Like first post
        homePage.likeAPost();

        // Confirm toast message for liked post is shown
        Assert.assertEquals(homePage.getLikedToastMessage(), "Post liked");
    }
}
