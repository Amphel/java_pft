package pft.learn.adressbook;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;


public class OrderCreation {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
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
    login();

  }

  private void login() {
    driver.get("https://leroymerlin.ru/");
    WebElement personalAccount = driver.findElement(By.xpath("//span[text()='Вход']"));
    personalAccount.click();
    WebElement iframe = driver.findElement(By.id("oauth-iframe"));
    driver.switchTo().frame(iframe);
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("testqualityapp@gmail.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin10071856");
    driver.findElement(By.id("login")).click();
    driver.switchTo().defaultContent();
  }

  @Test
  public void LeroyMerlinOrderCreation() throws Exception {

    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.className("owl-item")));

    Actions findCatalogue = new Actions(driver);
    WebElement catalogueMenu = driver.findElement(By.className("catalog-menu"));
    findCatalogue.moveToElement(catalogueMenu);
    findCatalogue.perform();


    Thread.sleep(500);


    Actions findStroymateriali = new Actions(driver);
    WebElement Stroymaterialy = driver.findElement(By.xpath("//a[@href='/catalogue/stroymaterialy/']"));
    findStroymateriali.moveToElement(Stroymaterialy);
    findStroymateriali.perform();

    Actions findSmesi = new Actions(driver);
    WebElement SukhieSmesi = driver.findElement(By.xpath("//a[@href='/catalogue/suhie-smesi-i-gruntovki/']"));
    findSmesi.moveToElement(SukhieSmesi);
    findSmesi.perform();

    // переход по ссылке Штукатурка
    Actions findShtukaturki = new Actions(driver);
    WebElement shtukaturki = driver.findElement(By.xpath("//a[@href='/catalogue/shtukaturki/']"));
    findShtukaturki.moveToElement(shtukaturki).click().perform();

    // Выбор одной номенклатуры
    WebElement item = driver.findElement(By.xpath("//div[@class='ui-product-card']//descendant::div[3]"));
    item.click();

    WebDriverWait waitForPicture = new WebDriverWait(driver, 10);
    waitForPicture.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@itemprop='image']")));

    //Кликнуть на "В корзину"
    WebElement shtukaturka = driver.findElement(By.xpath(
            "//div[@class='card-order-product-quantity-busked-button']"));
    shtukaturka.click();

//    WebDriverWait waitForCart = new WebDriverWait(driver, 10);
//    waitForCart.until(ExpectedConditions.visibilityOfElementLocated(By.className("shop-list-header")));

    Thread.sleep(2000);

    //Перейти в корзину
    WebElement cart = driver.findElement(By.xpath("//a[@href='/basket/']"));
    cart.click();
    //    Actions moveToCart = new Actions(driver);
//    moveToCart.moveToElement(cart).click();


    WebDriverWait waitForClearCartButton = new WebDriverWait(driver, 15);
    waitForClearCartButton.until(ExpectedConditions.elementToBeClickable(By.className("center-block")));

    //Очистить корзину
    WebElement clearCart = driver.findElement(By.className("all-delete-button"));
    clearCart.click();

    Thread.sleep(1000);

    //Да, очистить
    WebElement sureDeleteButton = driver.findElement(By.className("confirm-button"));
    sureDeleteButton.click();

    Thread.sleep(1000);

    //Войти в личный кабинет
    WebElement myPersonalAccount = driver.findElement(By.className("user-account-button"));
    myPersonalAccount.click();

    Thread.sleep(1000);

    //Выйти
    WebElement logout = driver.findElement(By.className("logout"));
    logout.click();
  }


  @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
        fail(verificationErrorString);
      }
    }

    private boolean isElementPresent (By by){
      try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent () {
      try {
        driver.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    private String closeAlertAndGetItsText () {
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
  }

