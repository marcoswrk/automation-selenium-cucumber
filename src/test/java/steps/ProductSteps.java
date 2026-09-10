package steps;

import core.BasePage;
import core.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class ProductSteps extends BasePage {

    @Given("i accessed the Products page")
    public void i_accessed_the_products_page() {
    clickLink("Products");
    }

    @When("i click on the first item view product button")
    public void i_click_on_the_first_item_view_product_button() {
    clickWithScroll(By.cssSelector("a[href='/product_details/1']"));
    }

    @Then("i get successfully redirected to the product details page")
    public void i_get_successfully_redirected_to_the_product_details_page() {
        Assertions.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("/product_details/1"));
    }
}
