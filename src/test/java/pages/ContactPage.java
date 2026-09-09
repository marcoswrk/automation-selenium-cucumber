package pages;

import core.BasePage;
import static utils.TestData.*;
import org.openqa.selenium.By;
import java.io.File;


public class ContactPage extends BasePage {

    public void setName() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.setName();
    }

    public void setContactEmail() {
        sendKeysCss("[data-qa='email']", generateRandomEmail());
    }

    public void setMessage() {
        sendKeysCss("[data-qa='subject']", generateLorem());
    }

    public void uploadFile(String filePath) {
        String absolutePath = new File(filePath).getAbsolutePath();
        driver.findElement(By.cssSelector("input[name='upload_file']")).sendKeys(absolutePath);
    }

    public void clickChooseFile() {
        uploadFile("src/test/resources/arquivo.txt");
    }

    public void clickSubmit() {
        clickCss("[data-qa='submit-button']");
    }
    @Override
    public void acceptAlert() {
        super.acceptAlert();
    }

    public String getSuccessUpload() {
        return getText(By.cssSelector(".status.alert.alert-success"));
    }
}
