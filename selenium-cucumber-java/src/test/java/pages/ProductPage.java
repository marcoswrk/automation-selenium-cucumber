package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import core.BasePage;
import core.Cart;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TestData;
import utils.TestData.*;
import java.util.List;

import static utils.TestData.*;

public class ProductPage extends BasePage {

    public String getOrderText() {
        return getText(By.cssSelector("h2.title.text-center"));
    }

    public String getAddressText(){
        return getText(By.cssSelector("div.step-one > h2.heading"));
    }
    public String getDressText(){
        return getText(By.cssSelector("a[href='/product_details/4']"));
    }

    public String getReviewText(){
        return    getText(By.cssSelector("#review-section .alert-success span"));
    }

    public String getShirtText(){
        return getText(By.cssSelector("a[href='/product_details/28']"));
    }


    public void clickViewProductById(String productId) {
        clickWithScroll(By.cssSelector(
                "a[href='/product_details/" + productId + "']"
        ));
    }

    public boolean isOnProductDetailsPage(String productId) {
        return wait.until(
                ExpectedConditions.urlContains("/product_details/" + productId)
        );
    }

    public void clickBrandProductName (String productName) {
        clickLinkCss("a[href='/brand_products/" + productName);
    }

    public void clickDressCategory () {
        clickLinkCss("a[href='#Women']");
        clickLinkCss("a[href='/category_products/1']");
    }

    public void subscribeNewsletter(String email) {
        email = TestData.generateRandomEmail();
        sendKeys("susbscribe_email", email);
        clickWithScroll(By.id("subscribe"));
    }

    public void clickProductById(String productId) {
        clickWithScroll(By.cssSelector("[data-product-id='" + productId + "']"));
    }

    public void addProductToCartById(String productId) {
        clickWithScroll(By.cssSelector("[data-product-id='" + productId + "']"));
    }

    public void clickModalButton(){
        driver.findElement(By.cssSelector(".btn-success")).click();
    }

    public void setProduct(String product) {
        sendKeys("search_product", product);
    }
public void clickSearchButton() {
    clickCss(".fa.fa-search");
}

    public String getCenterText() {
        return getText(By.cssSelector("h2[class='title text-center']"));
    }

    public boolean hasProductContaining(String expectedTerm) {
        List<WebElement> products = driver.findElements(
                By.cssSelector(".productinfo p")
        );

        return products.stream()
                .anyMatch(product ->
                        product.getText().toLowerCase()
                                .contains(expectedTerm.toLowerCase())
                );
    }

    public String successMessage() {
        return getText(By.cssSelector(".alert-success"));
    }

    public void interactCartModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));
    }

    public void scrollToSubscription() {
        WebElement subscription = driver.findElement(By.xpath("//h2[text()='Subscription']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(subscription).perform();
    }
    public void interactCheckOutModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutModal")));
    }

    public void setMessage() {
        sendKeys(By.cssSelector("textarea[name='message']"), generateLorem());
    }

    public String emptyCartMessage() {
        return getText(By.cssSelector("#empty_cart p b"));
    }

    public String congratsMessage() {
        return getText(By.cssSelector("h2[data-qa='order-placed'] + p"));
    }

    public List<Cart> getAllCartItems() {
        return getCartItems();
    }
    public Cart getCartByItemName(String name) {
        return  getAllCartItems().stream()
                .filter(item -> item.getDescription().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Item with name " + name + " not found"));

    }

    public void setReview() {
        sendKeys("name", generateFirstName());
        sendKeys("email", generateRandomEmail());
        sendKeys("review", generateLorem());
    }

    public void clickWithScroll(String css) {
        clickWithScroll(By.cssSelector(css));
    }

    public void clickScrollToTop() {
        click(By.cssSelector("a[href='#top']"));
    }

    public void addProductCart(){
        clickLink("Products");
        clickWithScroll(By.cssSelector("[data-product-id='3']"));
        interactCartModal();
        clickLink("View Cart");
    }

    public void clickCheckout(){
        click(By.cssSelector("a.check_out"));
    }
    public void clickCheckoutWithModal() {
        click(By.cssSelector("a.check_out"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutModal")));
    }
    public void setPayment (){
        sendKeys(By.cssSelector("input[data-qa='name-on-card']"), generateRandomName());
        sendKeys(By.cssSelector("input[data-qa='card-number']"), generateCardNumber());
        sendKeys(By.cssSelector("input[data-qa='cvc']"), generateCVC());
        sendKeys(By.cssSelector("input[data-qa='expiry-month']"), generateExpirationMonth());
        sendKeys(By.cssSelector("input[data-qa='expiry-year']"), generateExpirationYear());
    }
}