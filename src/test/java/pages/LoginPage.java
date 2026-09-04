package pages;
import static core.DriverFactory.getDriver;

import org.openqa.selenium.By;

import core.BasePage;

public class LoginPage extends BasePage {

    public void acessarLoginPage() {
        getDriver().get("https://automationexercise.com/login");
    }

    public void login(String email, String password) {
        sendKeysCss("[data-qa='login-email']", email);
        sendKeysCss("[data-qa='login-password']", password);
        clickLoginButton();
    }

    public void clickLoginButton() {
        clickCss("[data-qa='login-button']");
    }
public String getLoggedUser() {
    return getText(By.cssSelector("li a b"));
}

    public void incorrectLogin () {
        sendKeysCss("[data-qa='login-email']", "email@incorreto.com");
        sendKeysCss("[data-qa='login-password']", "senhaIncorreta");
        clickLoginButton();
    }

    public String getErrorMessage() {
        return getText(By.xpath("//p[normalize-space()='Your email or password is incorrect!']"));
    }
    public void clickLogoutButton() {
        clickLink("Logout");
    }

    public String getLogoutConfirmation(){
        return getText(By.tagName("h2"));
    }
}