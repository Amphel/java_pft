package ru.stqa.pft.leroymerlin.appmanager;

import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver driver;

    public NavigationHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void goToMainPage() {
      driver.get("https://leroymerlin.ru/");
    }
}
