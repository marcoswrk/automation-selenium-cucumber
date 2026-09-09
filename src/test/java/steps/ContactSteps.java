package steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.ContactPage;

public class ContactSteps {
    private ContactPage contactPage;

    @Given("i click on the Contact us button")
    public void i_click_on_the_contact_us_button() {
    contactPage = new ContactPage();
    contactPage.clickLink("Contact us");

    }
    @When("i fill out all information")
    public void i_fill_out_all_information() {
    contactPage.setName();
    contactPage.setContactEmail();
    contactPage.setMessage();
    contactPage.clickChooseFile();
    contactPage.clickSubmit();
    contactPage.acceptAlert();

    }
    @Then("i get a success message for submitting the forms")
    public void i_get_a_success_message_for_submitting_the_forms() {
    String uploadConfirmation = contactPage.getSuccessUpload();
    Assertions.assertEquals("Success! Your details have been submitted successfully.", uploadConfirmation);

    }
}
