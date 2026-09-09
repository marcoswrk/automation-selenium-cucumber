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

    @Given("i access the home page")
    public void i_access_the_home_page() {

    }
    @When("i click the button to access the Test Cases page")
    public void i_click_the_button_to_access_the_test_cases_page() {

    }
    @Then("i get successfully redirected to the Test Cases page")
    public void i_get_successfully_redirected_to_the_test_cases_page() {}


    @Given("i accessed the Products page")
    public void i_accessed_the_products_page() {}

    @When("i click on the first item view product button")
    public void i_click_on_the_first_item_view_product_button() {}


    @Then("i get successfully redirected to the product details page")
    public void i_get_successfully_redirected_to_the_product_details_page() {}

}
