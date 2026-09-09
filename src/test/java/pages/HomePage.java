package pages;

import core.BasePage;

import static core.DriverFactory.getDriver;

public class HomePage extends BasePage {

    public void accessHomePage () {
        getDriver().get("https://automationexercise.com/");
    }
}
