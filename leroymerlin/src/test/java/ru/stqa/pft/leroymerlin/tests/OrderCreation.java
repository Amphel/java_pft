package ru.stqa.pft.leroymerlin.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OrderCreation extends ru.stqa.pft.leroymerlin.tests.TestBase {

  @Test
  public void LeroyMerlinOrderCreation() throws Exception {

    app.login();
    addItemToCart();
    clearCart();
    app.logout();

  }

  private void clearCart() throws InterruptedException {
    //Перейти в корзину
    WebElement cart = app.driver.findElement(By.xpath("//a[@href='/basket/']"));
    cart.click();

    WebDriverWait waitForClearCartButton = new WebDriverWait(app.driver, 20);
    waitForClearCartButton.until(ExpectedConditions.elementToBeClickable(By.className("center-block")));

    //Очистить корзину
    WebElement clearCart = app.driver.findElement(By.className("all-delete-button"));
    clearCart.click();

    Thread.sleep(1000);

    //Да, очистить
    WebElement sureDeleteButton = app.driver.findElement(By.className("confirm-button"));
    sureDeleteButton.click();

    Thread.sleep(1000);
  }

  private void addItemToCart() throws InterruptedException {
    Actions findCatalogue = new Actions(app.driver);
    WebElement catalogueMenu = app.driver.findElement(By.className("catalog-menu"));
    findCatalogue.moveToElement(catalogueMenu);
    findCatalogue.perform();


    Thread.sleep(5000);


    Actions findStroymateriali = new Actions(app.driver);
    WebElement Stroymaterialy = app.driver.findElement(By.xpath("//a[@href='/catalogue/stroymaterialy/']"));
    findStroymateriali.moveToElement(Stroymaterialy);
    findStroymateriali.perform();

    Thread.sleep(5000);

    Actions findSmesi = new Actions(app.driver);
    WebElement SukhieSmesi = app.driver.findElement(By.xpath("//a[@href='/catalogue/suhie-smesi-i-gruntovki/']"));
    findSmesi.moveToElement(SukhieSmesi);
    findSmesi.perform();

    Thread.sleep(1000);

    // переход по ссылке Штукатурка
    Actions findShtukaturki = new Actions(app.driver);
    WebElement shtukaturki = app.driver.findElement(By.xpath("//a[@href='/catalogue/shtukaturki/']"));
    findShtukaturki.moveToElement(shtukaturki).click().perform();

    // Выбор одной номенклатуры
    WebElement item = app.driver.findElement(By.xpath("//div[@class='ui-product-card']//descendant::div[3]"));
    item.click();

    WebDriverWait waitForPicture = new WebDriverWait(app.driver, 10);
    waitForPicture.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@itemprop='image']")));

    //Кликнуть на "В корзину"
    WebElement shtukaturka = app.driver.findElement(By.xpath(
            "//div[@class='card-order-product-quantity-busked-button']"));
    shtukaturka.click();

    Thread.sleep(2000);
  }
}

