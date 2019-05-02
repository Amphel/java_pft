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


    addItemToCart();
    clearCart();
    logOutFromAccountLeroymerlin();

  }

  private void logOutFromAccountLeroymerlin() throws InterruptedException {
    //Войти в личный кабинет
    WebElement myPersonalAccount = app.getLeroymerlinHelper().driver.findElement(By.className("user-account-button"));
    myPersonalAccount.click();

    Thread.sleep(1000);

    //Выйти
    WebElement logout = app.getLeroymerlinHelper().driver.findElement(By.className("logout"));
    logout.click();
  }

  private void clearCart() throws InterruptedException {
    //Перейти в корзину
    WebElement cart = app.getLeroymerlinHelper().driver.findElement(By.xpath("//a[@href='/basket/']"));
    cart.click();
    //    Actions moveToCart = new Actions(driver);
//    moveToCart.moveToElement(cart).click();


    WebDriverWait waitForClearCartButton = new WebDriverWait(app.getLeroymerlinHelper().driver, 20);
    waitForClearCartButton.until(ExpectedConditions.elementToBeClickable(By.className("center-block")));

    //Очистить корзину
    WebElement clearCart = app.getLeroymerlinHelper().driver.findElement(By.className("all-delete-button"));
    clearCart.click();

    Thread.sleep(1000);

    //Да, очистить
    WebElement sureDeleteButton = app.getLeroymerlinHelper().driver.findElement(By.className("confirm-button"));
    sureDeleteButton.click();

    Thread.sleep(1000);
  }

  private void addItemToCart() throws InterruptedException {
    Actions findCatalogue = new Actions(app.getLeroymerlinHelper().driver);
    WebElement catalogueMenu = app.getLeroymerlinHelper().driver.findElement(By.className("catalog-menu"));
    findCatalogue.moveToElement(catalogueMenu);
    findCatalogue.perform();


    Thread.sleep(1000);


    Actions findStroymateriali = new Actions(app.getLeroymerlinHelper().driver);
    WebElement Stroymaterialy = app.getLeroymerlinHelper().driver.findElement(By.xpath("//a[@href='/catalogue/stroymaterialy/']"));
    findStroymateriali.moveToElement(Stroymaterialy);
    findStroymateriali.perform();

    Thread.sleep(1000);

    Actions findSmesi = new Actions(app.getLeroymerlinHelper().driver);
    WebElement SukhieSmesi = app.getLeroymerlinHelper().driver.findElement(By.xpath("//a[@href='/catalogue/suhie-smesi-i-gruntovki/']"));
    findSmesi.moveToElement(SukhieSmesi);
    findSmesi.perform();

    // переход по ссылке Штукатурка
    Actions findShtukaturki = new Actions(app.getLeroymerlinHelper().driver);
    WebElement shtukaturki = app.getLeroymerlinHelper().driver.findElement(By.xpath("//a[@href='/catalogue/shtukaturki/']"));
    findShtukaturki.moveToElement(shtukaturki).click().perform();

    // Выбор одной номенклатуры
    WebElement item = app.getLeroymerlinHelper().driver.findElement(By.xpath("//div[@class='ui-product-card']//descendant::div[3]"));
    item.click();

    WebDriverWait waitForPicture = new WebDriverWait(app.getLeroymerlinHelper().driver, 10);
    waitForPicture.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@itemprop='image']")));

    //Кликнуть на "В корзину"
    WebElement shtukaturka = app.getLeroymerlinHelper().driver.findElement(By.xpath(
            "//div[@class='card-order-product-quantity-busked-button']"));
    shtukaturka.click();

//    WebDriverWait waitForCart = new WebDriverWait(driver, 10);
//    waitForCart.until(ExpectedConditions.visibilityOfElementLocated(By.className("shop-list-header")));

    Thread.sleep(2000);
  }


}

