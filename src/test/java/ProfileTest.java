import object.HeaderElement;
import object.LoginPage;
import object.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProfileTest extends Helper {

    @DataProvider(name = "fillUserCredentials")
    public Object[][] fillUserCredentials() {
        return new Object[][] {
                {"valg", "test@mails.com", "Test123!"}
        };
    }
    @Test(dataProvider = "fillUserCredentials"/*, invocationCount = 5*/)
    public void testUserProfileData(String username, String email, String password) {
        // Login to profile
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.quickLogin(email, password);

        // Verify presence of Profile link and click on it
        HeaderElement header = new HeaderElement(super.getDriver());
        header.clickProfile();

        // Verify user profile page is loaded
        ProfilePage profilePage = new ProfilePage(super.getDriver());
        profilePage.isPageLoaded();

        // Validate correct username on profile page
        profilePage.getUserProfileName();
        Assert.assertEquals(profilePage.getUserProfileName(), username);

        // Get number of posts of user
        System.out.println(profilePage.printNumberOfPosts());

        // Get number of followers of user
        System.out.println(profilePage.getNumberOfFollowers());

        // Get number of following of user
        System.out.println(profilePage.getNumberOfFollowing());
    }
}
