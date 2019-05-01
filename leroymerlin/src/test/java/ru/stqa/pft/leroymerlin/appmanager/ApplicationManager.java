package ru.stqa.pft.leroymerlin.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
    public WebDriver driver;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private LeroymerlinHelper leroymerlinHelper;

    public boolean acceptNextAlert = true;
    public StringBuffer verificationErrors = new StringBuffer();

    public void init() {
        FirefoxProfile ffprofile = new FirefoxProfile();
        ffprofile.setPreference("dom.webnotifications.enabled", false);
        ffprofile.setPreference("geo.enabled", false);
        ffprofile.setPreference("geo.provider.use_corelocation", false);
        ffprofile.setPreference("geo.prompt.testing", false);
        ffprofile.setPreference("geo.prompt.testing.allow", false);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(ffprofile);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        leroymerlinHelper = new LeroymerlinHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        login();
    }

    public void login() {
      navigationHelper.goToMainPage();
      leroymerlinHelper.initSignInAccount();
      sessionHelper.fillSignInForm("testqualityapp@gmail.com", "admin10071856");
    }


    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
          fail(verificationErrorString);
        }
    }

    public boolean isElementPresent (By by){
        try {
          driver.findElement(by);
          return true;
        } catch (NoSuchElementException e) {
          return false;
        }
      }

    public boolean isAlertPresent () {
        try {
          driver.switchTo().alert();
          return true;
        } catch (NoAlertPresentException e) {
          return false;
        }
      }

    public String closeAlertAndGetItsText () {
        try {
          Alert alert = driver.switchTo().alert();
          String alertText = alert.getText();
          if (acceptNextAlert) {
            alert.accept();
          } else {
            alert.dismiss();
          }
          return alertText;
        } finally {
          acceptNextAlert = true;
        }
      }

    public LeroymerlinHelper getLeroymerlinHelper() {
        return leroymerlinHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
