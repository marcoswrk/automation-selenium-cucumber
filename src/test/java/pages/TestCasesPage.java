package pages;

import core.BasePage;
import org.openqa.selenium.By;

import static core.DriverFactory.getDriver;

public class TestCasesPage extends BasePage {

      public String getTestCases() {
        return getText(By.tagName("h2"));
    }


    public String getHomePage() {
        return getText(By.linkText("Home"));
    }

}
