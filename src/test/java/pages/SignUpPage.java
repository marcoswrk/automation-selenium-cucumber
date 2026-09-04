package pages;
import static core.DriverFactory.getDriver;
import core.BasePage;
import utils.TestData;
import org.openqa.selenium.By;

public class SignUpPage extends BasePage {
    private String generatedName;
    private String generatedEmail;
    private String generatedPassword;

    public void accessHomePage () {
        getDriver().get("https://automationexercise.com/");
    }

    public void setName() {
        generatedName = TestData.generateRandomName();
        sendKeysCss("[name='name']", generatedName);
    }

    public void setEmail() {
        generatedEmail = TestData.generateRandomEmail();
        sendKeysCss("[data-qa='signup-email']", generatedEmail);
    }
    public void setCredentialsEmail(String email){
        generatedEmail = email;
        sendKeysCss("[data-qa='signup-email']", generatedEmail);
    }
    public void clickSignupButton() {
        clickCss("[data-qa='signup-button']");
    }

    public String getGeneratedName() {
        return generatedName;
    }

    public String getGeneratedEmail() {
        return generatedEmail;
    }

    //enter account information page
    public void selectTitle() {
        clickById("id_gender1");
    }

    public void setPassword() {
        generatedPassword = TestData.generateRandomPassword();
        sendKeys(By.id("password"), generatedPassword);
    }
    public String getGeneratedPassword() {
        return generatedPassword;
    }

    public void selectDateOfBirth() {
        selectCombo("days", "10");
        selectCombo("months", "May");
        selectCombo("years", "1990");
    }

    public void selectNewsletter() {
        clickById("newsletter");
    }

    public void selectOffers() {
        clickById("optin");
    }

    // Adress Information Section
    public void setAddress() {
        sendKeys("first_name", TestData.generateFirstName());
        sendKeys("last_name", TestData.generateLastName());
        sendKeys("company", TestData.generateCompany());
        sendKeys("address1", TestData.generateStreetAddress());
        sendKeys("address2", TestData.generateSecondaryAddress());
        selectCombo("country", "United States");
        sendKeys("state", TestData.generateState());
        sendKeys("city", TestData.generateCity());
        sendKeys("zipcode", TestData.generateZipCode());
        sendKeys("mobile_number", TestData.generateMobileNumber());
    }
    
    public void clickCreateAccountButton() {
        clickCss("[data-qa='create-account']");
    }

    public String getAccountCreatedMessage() {
        return getText(By.cssSelector("[data-qa='account-created']"));
    }

    public String getErrorMessage() {
        return getText(By.xpath("//p[normalize-space()='Email Address already exist!']"));
    }
}
