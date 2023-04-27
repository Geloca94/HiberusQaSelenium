package EjercicioSeleniumJunit.Inventario;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import EjercicioSeleniumJunit.Login.LoginAceso;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class Inventario {


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
    //@Test
    @Ignore
    public void validationNumeroResultados(){

        //Paso 1
        List<WebElement> elementos = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        int cantidad = elementos.size();
        int cantidadEsperada = 6;

        //Paso 2
        Assert.assertEquals("ERROR: La cantidad no corresponde con la esperada:" , cantidadEsperada,cantidad);

    }
    @Ignore
    public void validationProducto(){

        boolean isPresent = false;

        String camisetaEsperada = "Sauce Labs Bolt T-Shirt";

        List<WebElement> listaProducto= driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        for(int i = 0; i < listaProducto.size(); i++){

           if( listaProducto.get(i).getText().equals(camisetaEsperada)){
               isPresent = true;
           }else {
               isPresent = false;
           }
        }

        Assert.assertTrue("El Resultado es ", isPresent);
        Assert.assertFalse("El Resultado es ", isPresent);



    }


    @Test
    public void addCarrito(){

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        buttonAdd.click();

        //Paso 2
        String carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();

        //Paso 3
        String carritoCorrecto = "1";

        //Paso 4

        Assert.assertEquals("ERROR: La cantidad no corresponde con la esperada:" , carritoCorrecto,carrito);



    }

    @Test
    public void deleteCarrito(){

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        buttonAdd.click();

        // Step 6 Compruebo que el boton aparece

        WebElement remove = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bolt-t-shirt']"));
        remove.click();

        String carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();


        try{

            String carritoCorrecto = "0";

            //Paso 4

            Assert.assertEquals("ERROR: La cantidad no corresponde con la esperada:" , carritoCorrecto,carrito);
        } catch (TimeoutException timeoutException){


        }



    }
    @After
    public void tearDown(){

        driver.close();

    }
}


