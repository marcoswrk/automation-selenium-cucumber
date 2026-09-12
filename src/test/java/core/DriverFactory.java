package core;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {
    // Para paralelismo
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void startDriver() {
        WebDriverManager.chromedriver().setup();
        DRIVER.set(new ChromeDriver());
        getDriver().manage().window().maximize();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        ChromeDriver driver = new ChromeDriver(options);
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