package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class NewPostPage {

    private WebDriver driver;
    private final String PAGE_URL = "http://training.skillo-bg.com:4200/posts/create";

    @FindBy(css = ".form-group")
    private WebElement form;

    @FindBy(css = "input.file")
    private WebElement uploadBrowseButton;

    @FindBy(name = "caption")
    private WebElement captionTextField;

    @FindBy(id = "create-post")
    private WebElement submitButton;

    @FindBy(css = "img.image-preview")
    private WebElement imagePreview;

    @FindBy(css = "input.input-lg")
    private WebElement uploadedImageName;

    @FindBy(css = "aria-label=\"Post created!\"")
    private WebElement toastMessagePostCreated;

    public NewPostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(PAGE_URL));
    }

    public void isFormLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(form));
    }

    public void populateCaption(String caption) {
        captionTextField.sendKeys(caption);
    }

    public void uploadPicture(File filePath) {
        uploadBrowseButton.sendKeys(filePath.getAbsolutePath());
    }

    public boolean isImagePreviewShown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOf(imagePreview)).isDisplayed();
    }

    public String getUploadedImageName() {
        return uploadedImageName.getAttribute("placeholder");
    }


    public void clickSubmitButton() {
        submitButton.click();
    }

    public String getNewPostToastMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(toastMessagePostCreated));
        return toastMessagePostCreated.getText().trim();
    }
}
