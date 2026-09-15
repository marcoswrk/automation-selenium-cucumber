package pages;
import pages.LoginPage;
import core.BasePage;
import core.Cart;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TestData.*;
import java.util.List;

import static utils.TestData.*;

public class ProductPage extends BasePage {

    public String searchedProductsTitle() {
        return getText(By.cssSelector("h2.title.text-center"));
    }

    public String deletedAccountText() {
    return getText(By.cssSelector("h2.title.text-center"));
    }

    public String successMessage() {
        return getText(By.cssSelector(".alert-success"));
    }

    public void interactCartModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));
    }

    public void interactCheckOutModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutModal")));
    }

    public void setMessage() {
        sendKeys(By.cssSelector("textarea[name='message']"), generateLorem());
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

    public void setPayment (){
        sendKeys(By.cssSelector("input[data-qa='name-on-card']"), generateRandomName());
        sendKeys(By.cssSelector("input[data-qa='card-number']"), generateCardNumber());;
        sendKeys(By.cssSelector("input[data-qa='cvc']"), generateCVC());
        sendKeys(By.cssSelector("input[data-qa='expiry-month']"), generateExpirationMonth());
        sendKeys(By.cssSelector("input[data-qa='expiry-year']"), generateExpirationYear());
    }
}