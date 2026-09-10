package steps;


import core.BasePage;
import core.DriverFactory;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;


public class TestCasesSteps extends BasePage {

    @When("i click the button to access the Test Cases page")
    public void i_click_the_button_to_access_the_test_cases_page() {
        clickLink("Test Cases");

    }

    @Then("i get successfully redirected to the Test Cases page")
    public void i_get_successfully_redirected_to_the_test_cases_page() {
        Assertions.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("/test_cases"));
}
}

