package ru.stqa.pft.leroymerlin.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.leroymerlin.model.LoginData;

public class LoginService {

    public WebDriver driver;
    protected NavigationHelper navigationHelper;

    public void login() {
      navigationHelper.goToMainPage();
      initSignInAccount();
      fillSignInForm(new LoginData("testqualityapp@gmail.com", "admin10071856"));
    }

    private void initSignInAccount() {
        click(By.xpath("//span[text()='Вход']"));
    }

    private void click(By locator) {
        driver.findElement(locator).click();
    }

    private void fillSignInForm(LoginData loginData) {
        WebElement iframe = driver.findElement(By.id("oauth-iframe"));
        driver.switchTo().frame(iframe);
        type(By.id("username"), loginData.getUsername());
        type(By.id("password"), loginData.getPassword());
        click(By.id("login"));
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("owl-item")));
    }

    private void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void logout() throws InterruptedException {
        //Войти в личный кабинет
        click(By.className("user-account-button"));

        Thread.sleep(1000);

        //Выйти
        click(By.className("logout"));
    }
}
