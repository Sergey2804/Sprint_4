package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


public class BaseTest {

    public WebDriver driver;

    // Метод запускает драйвер, который стоит по умолчанию в настройках Идеи
    @Before
    public void startUp() {
        String browser = System.getProperty("browser", "firefox");
        if (browser.equals("firefox")) {
            startBrowserFirefox();
        }else if (browser.equals("chrome")){
            startBrowserChrome();
        }
    }

    public void startBrowserFirefox() {
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
    }

    public void startBrowserChrome() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
    }

    // Метод закрывает браузер
    @After
    public void cleanUp() {
        // Закрываем сессию драйвера
        driver.quit();
    }

    // Метод для неявного ожидания с количеством секунд
    public void implicitlyWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

}
