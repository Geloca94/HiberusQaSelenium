package EjercicioSeleniumJunit.Carrito;

import EjercicioSeleniumJunit.Login.LoginAceso;
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

import java.util.concurrent.TimeUnit;

public class Carrito {


    LoginAceso loginAceso;

    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com/";


    @Before
    public void setUp(){

        loginAceso = new LoginAceso();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 5);


        driver.get(url);

        //loginAceso.Acceso();

        //USER
        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        //CONTRA
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        //BOTON
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();


    }


    @Test
    public void carrito(){

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        buttonAdd.click();
        WebElement buttonAdd2 = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']"));
        buttonAdd2.click();


        //Paso 2
        WebElement carritolink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        carritolink.click();

        String carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();

        try{

            //Paso 3
            String carritoCorrectoAntes = "2";
            Assert.assertEquals( carritoCorrectoAntes,carrito);
            System.out.println("La cantidad esperada es: " + carritoCorrectoAntes);
            System.out.println("La cantidad en el carrito obtenida es : " + carrito);
        } catch (AssertionError e){
            System.out.println("La cantidad es incorrecta, cantidad obtenida: " + carrito);
        }


        //paso 4
        WebElement remove = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-fleece-jacket']"));
        remove.click();

        carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();


        try{

            //Paso 5
            String carritoCorrectoDespues = "1";

            Assert.assertEquals( carritoCorrectoDespues,carrito);
            System.out.println("La cantidad esperada despues de eliminar un producto  es: " + carritoCorrectoDespues);
            System.out.println("La cantidad en el carrito obtenida despues de elminar el producto es : " + carrito);
        } catch (AssertionError e){
            System.out.println("La cantidad es incorrecta, cantidad obtenida: " + carrito);
        }



    }


    @After
    public void tearDown(){

        driver.close();

    }
}
