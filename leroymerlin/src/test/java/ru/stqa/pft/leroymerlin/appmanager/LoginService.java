package ru.stqa.pft.leroymerlin.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginService {

    public WebDriver driver;
    protected NavigationHelper navigationHelper;

    public void login() {
      navigationHelper.goToMainPage();
      initSignInAccount();
      fillSignInForm("testqualityapp@gmail.com", "admin10071856");
    }

    private void initSignInAccount() {
        WebElement personalAccount = driver.findElement(By.xpath("//span[text()='Вход']"));
        personalAccount.click();
    }

    private void fillSignInForm(String username, String password) {
        WebElement iframe = driver.findElement(By.id("oauth-iframe"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("owl-item")));
    }

    public void logout() throws InterruptedException {
        //Войти в личный кабинет
        WebElement myPersonalAccount = driver.findElement(By.className("user-account-button"));
        myPersonalAccount.click();

        Thread.sleep(1000);

        //Выйти
        WebElement logout = driver.findElement(By.className("logout"));
        logout.click();
    }
}
