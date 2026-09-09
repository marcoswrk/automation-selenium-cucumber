package steps;

import core.DriverFactory;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.TestCasesPage;

import java.time.Duration;


public class TestCasesSteps extends TestCasesPage {
    @When("i click the button to access the Test Cases page")
    public void i_click_the_button_to_access_the_test_cases_page() {
    TestCasesPage testPage = new TestCasesPage();
    testPage.clickLink("Test Cases");

    }

    @Then("i get successfully redirected to the Test Cases page")
    public void i_get_successfully_redirected_to_the_test_cases_page() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/test_cases"));

        Assertions.assertTrue(
                DriverFactory.getDriver().getCurrentUrl().contains("/test_cases")
        );
    }
}

