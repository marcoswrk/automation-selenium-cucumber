package core;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;


public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    
    public void sendKeys(By locator, String texto) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        campo.clear();
        campo.sendKeys(texto);
    }

    public void sendKeys(String id_campo, String texto) {
        sendKeys(By.id(id_campo), texto);
    }




    public void sendKeysCss(String css_id, String texto) {
        sendKeys(By.cssSelector(css_id), texto);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        dealWithGoogleVignette();
    }

    public void clickCss(String css_id) {
        click(By.cssSelector(css_id));
    }

    public void clickLink (String link) {
        click(By.partialLinkText(link));
    }
    public void clickLinkCss (String link) {
        click(By.cssSelector(link));
    }

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

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public void clickWithScroll(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    //Tratamento de anúncios da página
    private void dealWithGoogleVignette() {
        try {

            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            List<WebElement> iframes = driver.findElements(By.cssSelector("iframe[id^='aswift_'], iframe[id^='google_ads_iframe_']"));

            for (WebElement iframe : iframes) {
                try {
                    driver.switchTo().frame(iframe);

                    List<WebElement> dismissBtn = driver.findElements(By.cssSelector("#dismiss-button, div[aria-label='Close ad']"));
                    if (!dismissBtn.isEmpty() && dismissBtn.get(0).isDisplayed()) {
                        shortWait.until(ExpectedConditions.elementToBeClickable(dismissBtn.get(0))).click();
                        driver.switchTo().defaultContent();
                        return;
                    }

                    List<WebElement> innerIframe = driver.findElements(By.id("ad_iframe"));
                    if (!innerIframe.isEmpty()) {
                        driver.switchTo().frame(innerIframe.get(0));
                        WebElement innerDismiss = driver.findElement(By.cssSelector("#dismiss-button, div[aria-label='Close ad']"));
                        shortWait.until(ExpectedConditions.elementToBeClickable(innerDismiss)).click();
                        driver.switchTo().defaultContent();
                        return;
                    }
                    driver.switchTo().defaultContent();
                } catch (Exception e) {
                    driver.switchTo().defaultContent();
                }
            }
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
            }

        } catch (Exception e) {
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}
