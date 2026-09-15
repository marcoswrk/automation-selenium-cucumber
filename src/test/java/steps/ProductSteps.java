package steps;

import core.BasePage;
import core.Cart;
import core.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.ProductPage;
import utils.TestCredentials;
import utils.TestData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestData.*;


import pages.SignUpPage;

public class ProductSteps extends BasePage {

    private String generateEmail;
    private ProductPage productPage;
    private SignUpPage signUpPage;
    private LoginPage loginPage;

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
        wait.until(ExpectedConditions.urlContains("/product_details/1"));
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
        sendKeys("subscribe_email", generateEmail);
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
        productPage.interactCartModal();
        driver.findElement(By.cssSelector(".btn-success")).click();
    }

    @And("i add a second product to my cart")
    public void i_add_a_second_product_to_my_cart() {
        clickWithScroll(By.cssSelector("[data-product-id='2']"));
    }

    @When("i proceed to the checkout cart")
    public void i_proceed_to_the_checkout_cart() {
        productPage.interactCartModal();
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

    @Given("i have added a product to my cart")
    public void i_have_added_a_product_to_my_cart() {
        productPage = new ProductPage();
        clickLink("Products");
        clickWithScroll(By.cssSelector("[data-product-id='1']"));
        productPage.interactCartModal();
        driver.findElement(By.linkText("View Cart")).click();
        click(By.cssSelector("a.check_out"));
    }

    @And("i register myself on the website")
    public void i_register_myself_on_the_website() {
        signUpPage = new SignUpPage();
        productPage.interactCheckOutModal();
        signUpPage.Register();
        String message = signUpPage.getAccountCreatedMessage();
        Assertions.assertEquals("ACCOUNT CREATED!", message);
    }

    @When("i do the checkout and place my order")
    public void i_do_the_checkout_and_place_my_order() {
        clickLink("Cart");
        click(By.cssSelector("a.check_out"));
        productPage.setMessage();
        click(By.linkText("Place Order"));
        productPage.setPayment();
        click(By.id("submit"));

    }

    @And("i get a confirmation message for the order")
    public void i_get_a_confirmation_message_for_the_order() {
        productPage.searchedProductsTitle();
        assertEquals("ORDER PLACED!", productPage.searchedProductsTitle());
        productPage.congratsMessage();
        assertEquals("Congratulations! Your order has been confirmed!", productPage.congratsMessage());
    }

    @Then("i delete my account")
    public void i_delete_my_account() {
        clickLink("Delete Account");
        productPage.deletedAccountText();
        assertEquals("ACCOUNT DELETED!",  productPage.deletedAccountText());
        clickLink("Continue");
    }

    @Given("i register on the website")
    public void i_register_on_the_website() {
        productPage = new ProductPage();
        signUpPage = new SignUpPage();
        signUpPage.Register();
        String message = signUpPage.getAccountCreatedMessage();
        Assertions.assertEquals("ACCOUNT CREATED!", message);
    }

    @And("add products to my cart")
    public void add_products_to_my_cart() {
        clickLink("Products");
        clickWithScroll(By.cssSelector("[data-product-id='1']"));
        productPage.interactCartModal();
        driver.findElement(By.linkText("View Cart")).click();
        click(By.cssSelector("a.check_out"));
    }

    @When("i proceed to finish my order")
    public void i_proceed_to_finish_my_order() {
        productPage.setMessage();
        click(By.linkText("Place Order"));
        productPage.setPayment();
        click(By.id("submit"));
    }

    @Then("i get the order finished and delete my account")
    public void i_get_the_order_finished_and_delete_my_account() {
        productPage.searchedProductsTitle();
        assertEquals("ORDER PLACED!", productPage.searchedProductsTitle());
        productPage.congratsMessage();
        assertEquals("Congratulations! Your order has been confirmed!", productPage.congratsMessage());
        clickLink("Delete Account");
        productPage.deletedAccountText();
        assertEquals("ACCOUNT DELETED!",  productPage.deletedAccountText());
        clickLink("Continue");
    }

    @Given("i do login")
    public void i_do_login() {
        loginPage = new LoginPage();
        loginPage.login(TestCredentials.EMAIL, TestCredentials.PASSWORD);

    }

    @And("add products to cart and check out")
    public void add_products_to_cart_and_check_out() {
        clickLink("Products");
        clickWithScroll(By.cssSelector("[data-product-id='1']"));
        productPage.interactCartModal();
        driver.findElement(By.linkText("View Cart")).click();
        click(By.cssSelector("a.check_out"));
    }

    @When("i comment on text area and confirm order")
    public void i_comment_on_text_area_and_confirm_order() {
        productPage.setMessage();
        click(By.linkText("Place Order"));
        productPage.setPayment();
        click(By.id("submit"));
    }

    @Then("i get a confirmation message and delete account")
    public void i_get_a_confirmation_message_and_delete_account() {
        productPage.searchedProductsTitle();
        assertEquals("ORDER PLACED!", productPage.searchedProductsTitle());
        productPage.congratsMessage();
        assertEquals("Congratulations! Your order has been confirmed!", productPage.congratsMessage());
        clickLink("Delete Account");
        productPage.deletedAccountText();
        assertEquals("ACCOUNT DELETED!",  productPage.deletedAccountText());
        clickLink("Continue");
    }

    @Given("i navigate to the products page")
    public void i_navigate_to_the_products_page() {
        productPage = new ProductPage();
        clickLink("Products");
    }

    @When("i add a product and see it displayed")
    public void i_add_a_product_and_see_it_displayed() {
        clickWithScroll(By.cssSelector("[data-product-id='1']"));
        productPage.interactCartModal();
        driver.findElement(By.linkText("View Cart")).click();

    }

    @Then("i click on the x button and verify that the product is removed")
    public void i_click_on_the_x_button_and_verify_that_the_product_is_removed() {
        click(By.cssSelector("a.cart_quantity_delete[data-product-id='1']"));
        productPage.emptyCartMessage();
        assertEquals("Cart is empty!",  productPage.emptyCartMessage());
    }

    @Given("i verified the categories visible on the left side of products page")
    public void i_verified_the_categories_visible_on_the_left_side_of_products_page() {
        productPage = new ProductPage();
        clickLink("Products");
    }

    @When("i click on the Women category: Dress")
    public void i_click_on_the_women_category_dress() {
        clickLinkCss("a[href='#Women']");
        clickLinkCss("a[href='/category_products/1']");
    }

    @Then("i verify that the page displayed contains the expected text")
    public void i_verify_that_the_page_displayed_contains_the_expected_text() {
        assertEquals("WOMEN -  Dress PRODUCTS", getText(By.cssSelector("h2[class='title text-center']")));
    }

    @Given("i verified the brand visible on the left side of products page")
    public void i_verified_the_brand_visible_on_the_left_side_of_products_page() {
        clickLink("Products");
    }

    @When("i click on a brand name")
    public void i_click_on_a_brand_name() {
        clickLinkCss("a[href='/brand_products/Polo']");
    }

    @Then("i verify that the page displayed contains the expected brand products")
    public void i_verify_that_the_page_displayed_contains_the_expected_brand_products() {
        assertEquals("BRAND -  Polo PRODUCTS", getText(By.cssSelector("h2[class='title text-center']")));
    }

    @Given("i searched for products")
    public void i_searched_for_products() {
        productPage = new ProductPage();
        clickLink("Products");
        sendKeys("search_product", "Tshirts");
        clickCss(".fa.fa-search");
    }

    @And("i added this products to my cart")
    public void i_added_this_products_to_my_cart() {
        clickWithScroll(By.cssSelector("[data-product-id='28']"));
        productPage.interactCartModal();
        clickLink("View Cart");
        assertEquals("Pure Cotton V-Neck T-Shirt",  getText(By.cssSelector("a[href='/product_details/28']")));
    }

    @When("logged in the website")
    public void logged_in_the_website() {
        loginPage = new LoginPage();
        clickLink("Login");
        loginPage.login(TestCredentials.EMAIL, TestCredentials.PASSWORD);
    }

    @Then("i go to the cart page and the products are still added")
    public void i_go_to_the_cart_page_and_the_products_are_still_added() {
        clickLink("Cart");
        assertEquals("Pure Cotton V-Neck T-Shirt",  getText(By.cssSelector("a[href='/product_details/28']")));
    }

    @Given("i click on the products button")
    public void i_click_on_the_products_button() {
    clickLink("Products");
    }

    @And("i click on view product button")
    public void i_click_on_view_product_button() {
    clickWithScroll(By.cssSelector("a[href='/product_details/3']"));
    }

    @When("submit a review for the product")
    public void submit_a_review_for_the_product() {
    sendKeys("name", generateFirstName());
    sendKeys("email", generateRandomEmail());
    sendKeys("review", generateLorem());
    clickWithScroll(By.id("button-review"));
    }

    @Then("i get a success message for the review")
    public void i_get_a_success_message_for_the_review() {
    assertEquals("Thank you for your review.", getText(By.cssSelector("#review-section .alert-success span")));
    }

    @Given("i scroll to the bottom of the home page")
    public void i_scroll_to_the_bottom_of_the_home_page() {
        productPage = new ProductPage();
    }

    @When("i add a recommended product")
    public void i_add_a_recommended_product() {
        clickWithScroll(By.cssSelector("[data-product-id='4']"));
        productPage.interactCartModal();
        clickLink("View Cart");
    }

    @Then("i get the product displayed in the cart page")
    public void i_get_the_product_displayed_in_the_cart_page() {
        assertEquals("Stylish Dress",  getText(By.cssSelector("a[href='/product_details/4']")));

    }

    @Given("i create my account and add products to my cart")
    public void i_create_my_account_and_add_products_to_my_cart() {
        signUpPage = new SignUpPage();
        signUpPage.Register();
        productPage = new ProductPage();
        clickLink("Products");
        clickWithScroll(By.cssSelector("[data-product-id='3']"));
        productPage.interactCartModal();
        clickLink("View Cart");
    }

    @When("i proceed to the checkout page")
    public void i_proceed_to_the_checkout_page() {
        click(By.cssSelector("a.check_out"));
    }

    @Then("i confirm address and billing address are correct and delete my account")
    public void i_confirm_address_and_billing_address_are_correct_and_delete_my_account() {
        assertEquals("Address Details", getText(By.cssSelector("div.step-one > h2.heading")));
        clickLink("Delete Account");
        productPage.deletedAccountText();
        assertEquals("ACCOUNT DELETED!",  productPage.deletedAccountText());
        clickLink("Continue");
    }

    @Given("i add products to cart and register on the website")
    public void i_add_products_to_cart_and_register_on_the_website() {
        signUpPage = new SignUpPage();
        productPage = new ProductPage();
        clickLink("Products");
        clickWithScroll(By.cssSelector("[data-product-id='3']"));
        productPage.interactCartModal();
        clickLink("View Cart");
        click(By.cssSelector("a.check_out"));
        productPage.interactCheckOutModal();
        clickLink("Register / Login");
        signUpPage.Register();
    }

    @When("i get successfully proceeded to confirm my order")
    public void i_get_successfully_proceeded_to_confirm_my_order() {
        String message = signUpPage.getAccountCreatedMessage();
        Assertions.assertEquals("ACCOUNT CREATED!", message);
        clickLink("Cart");
        click(By.cssSelector("a.check_out"));
        productPage.setMessage();
        click(By.linkText("Place Order"));
        productPage.setPayment();
        click(By.id("submit"));
    }

    @Then("i get to successfully download the invoice and delete my account")
    public void i_get_to_successfully_download_the_invoice_and_delete_my_account() {
        productPage.searchedProductsTitle();
        assertEquals("ORDER PLACED!", productPage.searchedProductsTitle());
        productPage.congratsMessage();
        assertEquals("Congratulations! Your order has been confirmed!", productPage.congratsMessage());
        clickLink("Download Invoice");
    }

    @Given("i navigate to the bottom page")
    public void i_navigate_to_the_bottom_page() {
        productPage = new ProductPage();
        productPage.scrollToSubscription();
    }

    @When("i click o the arrow button at the botton right of the page")
    public void i_click_o_the_arrow_button_at_the_botton_right_of_the_page() {
        click(By.cssSelector("a[href='#top']"));
    }

    @Then("i verify that page is scrolled up")
    public void i_verify_that_page_is_scrolled_up() {
        assertEquals("Full-Fledged practice website for Automation Engineers", getText(By.cssSelector("h2")));
    }
}
