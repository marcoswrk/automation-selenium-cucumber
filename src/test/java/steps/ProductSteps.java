package steps;

import core.BasePage;
import core.Cart;
import core.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pages.ProductPage;
import utils.TestData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSteps extends BasePage {

    private String generateEmail;
    private ProductPage productPage;

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
        assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("/product_details/1"));
    }

    @Given("i accessed the Products page for search")
    public void i_accessed_the_products_page_for_search() {
        clickLink("Products");
    }

    @When("i put a product name and and click search button")
    public void i_put_a_product_name_and_and_click_search_button() {
        sendKeys("search_product", "Top");
        clickCss(".fa.fa-search");
    }

    @Then("i get all the products related search visible")
    public void i_get_all_the_products_related_search_visible() {
        productPage = new ProductPage();
        assertEquals("SEARCHED PRODUCTS", productPage.searchedProductsTitle());
        validateProductsContainAtLeastOne(By.cssSelector(".productinfo p"), ("Top"));
    }

    @When("i put my email and click over the arrow")
    public void i_put_my_email_and_click_over_the_arrow() {
        generateEmail = TestData.generateRandomEmail();
        sendKeys("susbscribe_email", generateEmail);
        clickWithScroll(By.id("subscribe"));
    }

    @Then("i receive a message for successfully subscribing")
    public void i_receive_a_message_for_successfully_subscribing() {
        productPage = new ProductPage();
        assertEquals("You have been successfully subscribed!", productPage.successMessage());
    }

    @Given("i accessed the cart page")
    public void i_accessed_the_cart_page() {
    clickLink("Cart");
    }

    @When("i put my email and click over the arrow at cart page")
    public void i_put_my_email_and_click_over_the_arrow_at_cart_page() {
        generateEmail = TestData.generateRandomEmail();
        sendKeys("susbscribe_email", generateEmail);
        clickWithScroll(By.id("subscribe"));
    }

    @Then("i receive a message for successfully subscribing via cart page")
    public void i_receive_a_message_for_successfully_subscribing_via_cart_page() {
        productPage = new ProductPage();
        assertEquals("You have been successfully subscribed!", productPage.successMessage());
    }

    @Given("i have already added the first product to my cart")
    public void i_have_already_added_the_first_product_to_my_cart() {
        productPage = new ProductPage();
        clickLink("Products");
        clickWithScroll(By.cssSelector("[data-product-id='1']"));
    }

    @Given("i click on the continue shopping button")
    public void i_click_on_the_continue_shopping_button() {
        productPage.interactModal();
        driver.findElement(By.cssSelector(".btn-success")).click();
    }

    @When("i add a second product to my cart")
    public void i_add_a_second_product_to_my_cart() {
        clickWithScroll(By.cssSelector("[data-product-id='2']"));
    }

    @When("i proceed to the checkout cart")
    public void i_proceed_to_the_checkout_cart() {
        productPage.interactModal();
        driver.findElement(By.linkText("View Cart")).click();
    }

    @Then("i should see both products and prices in my shopping cart")
    public void i_should_see_both_products_and_prices_in_my_shopping_cart() {
        Cart blueTop = productPage.getCartByItemName("Blue Top");
        assertEquals("Rs. 500", blueTop.getPrice());
        assertEquals("1", blueTop.getQuantity());

        Cart tshirt = productPage.getCartByItemName("Men Tshirt");
        assertEquals("Rs. 400", tshirt.getPrice());
        assertEquals("1", tshirt.getQuantity());
    }
}
