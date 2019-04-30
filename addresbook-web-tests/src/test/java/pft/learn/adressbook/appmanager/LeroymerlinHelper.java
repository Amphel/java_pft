package pft.learn.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeroymerlinHelper {

    public WebDriver driver;

    public LeroymerlinHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void initSignInAccount() {
      WebElement personalAccount = driver.findElement(By.xpath("//span[text()='Вход']"));
      personalAccount.click();
    }
}
