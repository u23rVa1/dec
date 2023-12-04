import object.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends Base {
    @DataProvider(name = "fillUsername")
    public Object[][] fillUsername() {
        return new Object[][] {
                {"testvg", "Test123!"},
                {"ran@dom.com", "Test123!"}
        };
    }

    @Test(dataProvider = "fillUsername", invocationCount = 5)
    public void testLoginPage(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.gotoPage();

        loginPage.isPageLoaded();

        loginPage.checkPresenceOfSignInForm();

        loginPage.fillUsernameOrEmail(username);

        loginPage.fillPassword(password);

        loginPage.clickSignInButton();
    }
}
