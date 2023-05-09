package EjercicioSeleniumJunit.Login;

import EjercicioSeleniumJunit.Page.PagesFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Login {


  WebDriver driver;
  WebDriverWait wait;
  String url = "https://www.saucedemo.com/";

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @Test
    public void validationLoginCorrecto(){

        driver.get(url);

        //USER
        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        //CONTRA
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        //BOTON
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        String actualUrl = driver.getCurrentUrl();
        String urlEsperada = "https://www.saucedemo.com/inventory.html";

        Assert.assertEquals("ERROR: La pagina no corresponde con la esperada", urlEsperada, actualUrl);
    }
    @Test
    public void validationLoginIncorrecto(){

        driver.get(url);

        //USER
        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_usr");

        //CONTRA
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        //BOTON
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        //MENSAJE ERROR
        WebElement errorMessage = null;
        try {
            errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        }catch (NoSuchElementException nsee){
            Assert.fail("ERROR: El mensaje de 'Login Incorrecto' no se visualiza");
        }






    }

    @After
    public void tearDown(){

        driver.close();

    }
}
