package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
    public void click(WebElement element) {
        try {
            // Tenta o clique real padrão do Selenium
            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // CORREÇÃO: Usando o método público getDriver() para acessar a thread segura
            System.out.println("[INFO] Clique interceptado por anuncio. Forçando execucao via JavaScript Executor.");
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) DriverFactory.getDriver();
            js.executeScript("arguments[0].click();", element); // Corrigido também o índice do argumento do JS
        }
    }


    public void clickCss(String css_id) {
        click(By.cssSelector(css_id));
    }

    public void submitForm(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.submit();
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
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public void clickWithScroll(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void validateProductsContainAtLeastOne(By locator, String expectedTerm){
        List<WebElement> products = driver.findElements(locator);
            boolean found = products.stream()
                    .anyMatch(p -> p.getText().toLowerCase().contains(expectedTerm.toLowerCase()));

            if (!found) {
                throw new AssertionError("Nenhum produto contém o termo: " + expectedTerm);
            }
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

    public List<Cart> getCartItems() {
        List<Cart> items = new ArrayList<>();
        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("table.table-condensed tbody tr"))
        );

        for (WebElement row : rows) {
            String imageSrc = row.findElement(By.cssSelector(".cart_product img")).getAttribute("src");
            String description = row.findElement(By.cssSelector(".cart_description h4 a")).getText();
            String price = row.findElement(By.cssSelector(".cart_price p")).getText();
            String quantity = row.findElement(By.cssSelector(".cart_quantity button")).getText();
            String total = row.findElement(By.cssSelector(".cart_total_price")).getText();

            items.add(new Cart(imageSrc, description, price, quantity, total));
        }

        return items;
    }

}
