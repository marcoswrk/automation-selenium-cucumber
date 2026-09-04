package core;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class DriverFactory {
    // Para paralelismo
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void startDriver() {
        WebDriverManager.chromedriver().setup();
        DRIVER.set(new ChromeDriver());
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            throw new IllegalStateException("Driver não inicializado");
        }
        return DRIVER.get();
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}