import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class SampleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
       // ChromeOptions options = new ChromeOptions();
      //  options.addArguments("headless"); // браузер откроется в фоне
        driver = new ChromeDriver(); //для хэдлеса дописать опшн в скобки
        logger.info("Драйвер поднят в режиме Headless");
    }
    @Test
    public void openPage(){
        driver.get(cfg.url());
        logger.info("Открыта страница Dot-Dot");
    }
    @Test
    public void getCookies(){
        driver.get(cfg.url());
        driver.manage().addCookie(new Cookie("Otus1","Value1") );
        driver.manage().addCookie(new Cookie("Otus2","Value2") );
        Cookie cookie = new Cookie("Otus3","Value3");
        driver.manage().addCookie(cookie);
        driver.manage().addCookie(new Cookie("Otus4","Value4") );
        logger.info(driver.manage().getCookies().toString());
        logger.info(driver.manage().getCookieNamed("Otus1"));
        driver.manage().addCookie(new Cookie("yandexuid","5559046871598960366"));
        driver.manage().deleteCookie(cookie);
    }

    @Test
    public void TimeOut() {
        driver.get(cfg.url());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(new By.ByClassName("vs__dropdown-toggle")).click(); // дождаться появление элемента и кликнуть
        //logger.info(w1.isDisplayed());
        driver.manage().window().setPosition(new Point(20,20));
    }
    @Test
    public void window(){
        driver.get(cfg.url());
        driver.manage().window().setPosition(new Point(30,30));
        driver.manage().window().fullscreen();
        Dimension dimension = new Dimension(70,70) ;
        driver.manage().window().setSize(dimension);

        logger.info(driver.manage().window().getPosition());
    }
    @Test
    public void yandex(){
        driver.get(cfg.url());
        driver.findElement(new By.ByXPath("//*[@id=\"text\"]")).click();
        driver.findElement(By.id("text")).sendKeys("otus");
        /* нажать кнопку поиск*/
        //driver.findElement(new By.ByXPath("/html/body/table/tbody/tr[2]/td/form/div[2]/button")).click();

    }



   /* @After
    public void setDown(){
        if(driver != null){
            driver.quit();
        }
    }*/
}
