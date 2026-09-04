package hooks;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

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
