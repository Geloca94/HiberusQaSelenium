package EjercicioSeleniumJunit.Support;

import EjercicioSeleniumJunit.Pages.PagesFactory;
import EjercicioSeleniumJunit.Utils.Flags;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.jsoup.internal.StringUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.*;

@Slf4j
public class Hooks {

    public static WebDriver driver;
    @Before
    public void before(Scenario scenario){
        log.info("starting" + scenario.getName());
        String browser = Flags.getInstance().getBrowser();
        if(StringUtils.isBlank(browser))browser = CHROME;
        boolean isHeadless = Flags.getInstance().isHeadless();
        switch (browser){
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(isHeadless){
                    firefoxOptions.addArguments("--hradless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if(isHeadless){
                    chromeOptions.addArguments("--hradless");
                }
                driver = new ChromeDriver(chromeOptions);

        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        PagesFactory.start(driver);


    }

    @After
    public void after(Scenario scenario){

        driver.close();

    }
}
