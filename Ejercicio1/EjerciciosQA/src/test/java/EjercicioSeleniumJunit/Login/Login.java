package EjercicioSeleniumJunit.Login;

import EjercicioSeleniumJunit.Pages.InventoryPage;
import EjercicioSeleniumJunit.Pages.LoginPage;
import EjercicioSeleniumJunit.Pages.PagesFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
public class Login {

  WebDriver driver;
  LoginPage loginPage;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PagesFactory.start(driver);
        driver.get(LoginPage.PAGE_URL);
        PagesFactory pagesFactory = PagesFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();

    }

    @Test
    public void validationLoginCorrecto(){

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertEquals("ERROR: La pagina no corresponde con la esperada",
          InventoryPage.PAGE_URL, driver.getCurrentUrl());
    }
    @Test
    public void validationLoginIncorrecto(){



        PagesFactory pagesFactory = PagesFactory.getInstance();
        LoginPage loginPage = pagesFactory.getLoginPage();

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("xxx");
        loginPage.clickLogin();


        Assert.assertTrue("the user was correct",
                loginPage.hasUsernamePasswordError());
    }

    @After
    public void tearDown(){

        driver.close();

    }
}
