package pages;

import core.BasePage;
import static utils.TestData.*;
import static utils.TestData.generateLorem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;


public class ContactPage extends BasePage {

    public void setName() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.setName();
    }

    public void setContactEmail() {
        sendKeysCss("[data-qa='email']", generateRandomEmail());
    }

    public void setSubject() {
        sendKeysCss("[data-qa='subject']", generateLorem());
    }

    public void setMessage() {
        sendKeys("message", generateLorem());
    }

    public void uploadFile(String filePath) {
        String absolutePath = new File(filePath).getAbsolutePath();
        driver.findElement(By.cssSelector("input[name='upload_file']")).sendKeys(absolutePath);
    }

    public void selectFile() {
        uploadFile("src/test/resources/arquivo.txt");
    }

        public void submitForm() {
        submitForm(By.cssSelector("input[type='submit']"));
    }

    @Override
    public void acceptAlert() {
        super.acceptAlert();
    }

    public String getSuccessUpload() {
        return getText(By.cssSelector(".status.alert.alert-success"));
    }
}
