package pft.learn.adressbook.appmanager;

import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    public WebDriver driver;

    public NavigationHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void goToMainPage() {
      driver.get("https://leroymerlin.ru/");
    }
}
