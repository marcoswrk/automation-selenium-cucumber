package hooks;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.startDriver();
        DriverFactory.getDriver().get("https://automationexercise.com/");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
