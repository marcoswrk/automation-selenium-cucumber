package core;
<<<<<<< HEAD
=======
import org.openqa.selenium.Alert;
>>>>>>> b119548 (mensagem explicando a alteração)
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

<<<<<<< HEAD
=======
import java.io.File;
>>>>>>> b119548 (mensagem explicando a alteração)
import java.time.Duration;


public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void sendKeys(By locator, String texto) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        campo.clear();
        campo.sendKeys(texto);
    }

    public void sendKeys(String id_campo, String texto) {
        sendKeys(By.id(id_campo), texto);
    }
<<<<<<< HEAD

=======
>>>>>>> b119548 (mensagem explicando a alteração)
    public void sendKeysCss(String css_id, String texto) {
        sendKeys(By.cssSelector(css_id), texto);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clickCss(String css_id) {
        click(By.cssSelector(css_id));
    }
<<<<<<< HEAD

    public void clickLink (String link) {
        click(By.linkText(link));
    }

=======
    public void clickLink (String link) {
        click(By.linkText(link));
    }
>>>>>>> b119548 (mensagem explicando a alteração)
    public void clickById (String id) {
        click(By.id(id));
    }

    public void selectCombo(String id, String valor) {
        WebElement element = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.id(id))
        );
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }
<<<<<<< HEAD
=======

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }
>>>>>>> b119548 (mensagem explicando a alteração)
}
