package pages;

import core.BasePage;
import core.Cart;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage {
    public String searchedProductsTitle() {
        return getText(By.cssSelector("h2.title.text-center"));
    }

    public String successMessage() {
        return getText(By.cssSelector(".alert-success"));
    }

    public void interactModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));

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
}