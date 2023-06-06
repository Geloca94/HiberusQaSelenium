package EjercicioSeleniumJunit.Logout;

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

public class Logout {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;


    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PagesFactory.start(driver);
        driver.get(InventoryPage.PAGE_URL);
        PagesFactory pagesFactory = PagesFactory.getInstance();

        inventoryPage = pagesFactory.getInventoryPage();

        //Acceso a la PAgina
        loginPage = pagesFactory.getLoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

    }

    @Test
    public void logout() throws InterruptedException {

        inventoryPage.clickMenuOut();
        Assert.assertEquals("ERROR: La pagina no corresponde con la esperada",
                LoginPage.PAGE_URL, driver.getCurrentUrl());
    }

    @After
    public void tearDown(){

        driver.close();

    }
}
