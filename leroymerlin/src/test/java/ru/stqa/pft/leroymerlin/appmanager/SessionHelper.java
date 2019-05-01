package ru.stqa.pft.leroymerlin.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionHelper {
    public WebDriver driver;

    public SessionHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void fillSignInForm(String username, String password) {
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

}
