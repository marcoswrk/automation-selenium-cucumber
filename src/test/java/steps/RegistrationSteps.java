package steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import pages.LoginPage;
import pages.SignUpPage;
import utils.TestCredentials;

public class RegistrationSteps {

    private SignUpPage signUpPage;
    private LoginPage loginPage;

    @Given("i click on the signup button for register")
    public void i_click_on_the_signup_button_for_register() {
        signUpPage = new SignUpPage();
        signUpPage.clickLink("Signup / Login");
    }

    @When("i register my credentials")
    public void i_register_my_credentials() {
        signUpPage.setName();
        signUpPage.setEmail();
        signUpPage.clickSignupButton();
        signUpPage.selectTitle();
        signUpPage.setPassword();
        signUpPage.selectDateOfBirth();
        signUpPage.selectNewsletter();
        signUpPage.selectOffers();
        signUpPage.setAddress();
        //Imprimir para referência de Login
        //System.out.println("Email gerado: " + signUpPage.getGeneratedEmail());
        signUpPage.clickCreateAccountButton();
        
    }

    @Then("i get the signup completed")
    public void i_get_the_signup_completed() {
        String message = signUpPage.getAccountCreatedMessage();
        Assertions.assertEquals("ACCOUNT CREATED!", message);
    }

    @Given("i click on the login button")
    public void i_click_on_the_login_button() {
        loginPage = new LoginPage();
        loginPage.acessarLoginPage();
    }

    @When("i put registered credentials")
    public void i_put_registered_credentials() {
        loginPage.login(TestCredentials.EMAIL, TestCredentials.PASSWORD);
    }

    @Then("i get the login completed")
    public void i_get_the_login_completed() {
        String loggedUser = loginPage.getLoggedUser();
        Assertions.assertEquals("Dr. Connie Tremblay", loggedUser);
    }

    @Given("i click on the login button to login")
    public void i_click_on_the_login_button_to_login() {
     loginPage = new LoginPage();
     loginPage.acessarLoginPage();
    }

    @When("i register wrong credentials")
    public void i_register_wrong_credentials() {
        loginPage.incorrectLogin();
    }

    @Then("i get a warning to correct email and password")
    public void i_get_a_warning_to_correct_email_and_password() {
        String errorMessage = loginPage.getErrorMessage();
        Assertions.assertEquals("Your email or password is incorrect!", errorMessage);
    }

    @Given("i am already logged")
    public void i_am_already_logged() {
        loginPage = new LoginPage();
        loginPage.acessarLoginPage();
        loginPage.login(TestCredentials.EMAIL, TestCredentials.PASSWORD);
    }

    @When("i click on the logout button")
    public void i_click_on_the_logout_button() {
        loginPage.clickLogoutButton();
    }
    
    @Then("i get successfully logged out")
    public void i_get_successfully_logged_out() {
        String logoutConfirmation = loginPage.getLogoutConfirmation();
        Assertions.assertEquals("Login to your account", logoutConfirmation);
    }

    @Given("i click on the signup button")
    public void i_click_on_the_signup_button() {
        signUpPage = new SignUpPage();
        signUpPage.clickLink("Signup / Login");
    }

    @When("i register an already used email")
    public void i_register_an_already_used_email() {
        signUpPage.setName();
        signUpPage.setCredentialsEmail(TestCredentials.EMAIL);
        signUpPage.clickSignupButton();
    }

    @Then("i get an error for the email")
    public void i_get_an_error_for_the_email() {
        String errorMessage = signUpPage.getErrorMessage();
        Assertions.assertEquals("Email Address already exist!", errorMessage);
    }
}

