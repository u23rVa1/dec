import object.HeaderElement;
import object.LoginPage;
import object.NewPostPage;
import object.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class CreateNewPostTest extends Helper {

    @DataProvider(name = "fillUserData")
    public Object[][] fillUserData() {

        File postPicture = new File("src\\main\\resources\\upload\\private.jpg");

        return new Object[][] {
                {"valg", "Test123!", postPicture}
        };
    }

    @Test(dataProvider = "fillUserData")
    public void testCreateNewPost(String username, String password, File postPicture) {
        // Login to profile
        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.quickLogin(username, password);

        // Verify presence of Profile link and click on it
        HeaderElement header = new HeaderElement(super.getDriver());
        header.clickProfile();

        // Verify user profile page is loaded
        ProfilePage profilePage = new ProfilePage(super.getDriver());
        profilePage.isPageLoaded();

        // Validate correct username is on profile page
        profilePage.getUserProfileName();
        Assert.assertEquals(profilePage.getUserProfileName(), username);

        // Get current number of posts of user
        int initialPostsCount = profilePage.getNumberOfPostsCount();

        // Click on New Post button
        header.clickNewPost();

        // Verify page and form are loaded
        NewPostPage newPostPage = new NewPostPage(super.getDriver());
        newPostPage.isPageLoaded();
        newPostPage.isFormLoaded();

        // Upload picture
        newPostPage.uploadPicture(postPicture);

        // Verify image uploaded/image preview is shown
        Assert.assertTrue(newPostPage.isImagePreviewShown());

        // Verify uploaded image name
        String imgName = newPostPage.getUploadedImageName();
        Assert.assertEquals(imgName, postPicture.getName());

        // Enter post caption
        String caption = generateString(6, 10);
        newPostPage.populateCaption(caption);

        // Click on Submit button
        newPostPage.clickSubmitButton();

        // Verify profile page redirect
        profilePage.isPageLoaded();

        // Get updated number of posts of user
        int updatedPostsCount = profilePage.getNumberOfPostsCount();
        Assert.assertEquals(initialPostsCount+1, updatedPostsCount);
    }
}
