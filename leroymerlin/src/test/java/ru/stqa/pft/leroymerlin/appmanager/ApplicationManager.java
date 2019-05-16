package ru.stqa.pft.leroymerlin.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.fail;

public class ApplicationManager {

    public WebDriver driver;
    private LoginService loginService = new LoginService();
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            FirefoxProfile ffprofile = new FirefoxProfile();
            ffprofile.setPreference("dom.webnotifications.enabled", false);
            ffprofile.setPreference("geo.enabled", false);
            ffprofile.setPreference("geo.provider.use_corelocation", false);
            ffprofile.setPreference("geo.prompt.testing", false);
            ffprofile.setPreference("geo.prompt.testing.allow", false);

            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(ffprofile);

            driver = new FirefoxDriver(options);

        } else if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginService.navigationHelper = new NavigationHelper(driver);
        loginService.driver = driver;
    }

    public void stop() throws InterruptedException {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
          fail(verificationErrorString);
        }
    }

    public void login() throws InterruptedException {
        loginService.login();
    }

    public void logout() throws InterruptedException {
        loginService.logout();
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

    public NavigationHelper getNavigationHelper() {
        return loginService.navigationHelper;
    }
}
