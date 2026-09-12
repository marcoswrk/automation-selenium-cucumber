package hooks;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.startDriver();
        DriverFactory.getDriver().get("https://automationexercise.com/");
    }

    @After
    public void tearDown(Scenario scenario) {
        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot - " + scenario.getName(), new ByteArrayInputStream(screenshot));
        DriverFactory.quitDriver();
    }
}
