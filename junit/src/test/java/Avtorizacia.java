import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.COMMAND;
import static org.openqa.selenium.Keys.ENTER;

public class Avtorizacia {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Драйвер поднят");
    }
    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void avtorizacia() throws InterruptedException {
        driver.get(cfg.url());
        logger.info("Открыта страница Dot-Dot");
        String login = "1234567890";
        String password = "0a202de290";
        String locator = "header-login";
        driver.findElement(By.className(locator)).click();
        driver.findElement(By.name("login")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password")).sendKeys(ENTER);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.className("mobile-logged")).click();
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/ul[2]/li[1]/ul/li[1]/a")).click();
        driver.findElement(By.className("edit-profile")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[3]/div[1]/div/input")).sendKeys(COMMAND,"A","\b");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[3]/div[1]/div/input")).sendKeys("МарьИванна");
        //скролл до нужного объекта
        Object webElement = driver.findElement(By.className("btn-default"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        Thread.sleep(500);
        driver.quit();
        driver.get(cfg.url());
        String login = "1234567890";
        String password = "0a202de290";
        String locator = "header-login";
        driver.findElement(By.className(locator)).click();
        driver.findElement(By.name("login")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.className("mobile-logged")).click();
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/ul[2]/li[1]/ul/li[1]/a")).click();
        //сравнить поле






    }


}
