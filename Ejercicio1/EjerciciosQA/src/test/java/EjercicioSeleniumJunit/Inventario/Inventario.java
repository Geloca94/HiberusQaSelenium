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


import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;
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

    @Test
    public void validationNumeroResultados(){

        //Paso 1
        List<WebElement> elementos = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        //Paso 2
        try{

            int cantidad = elementos.size();
            int cantidadEsperada = 6;

            Assert.assertEquals( cantidadEsperada,cantidad);
            System.out.println("La cantidad esperada es: " + cantidadEsperada);
            System.out.println("La cantidad en el carrito obtenida es : " + cantidad);
        } catch (AssertionError e){
            System.out.println("La cantidad es incorrecta, cantidad obtenida es incorrecta " );
        }



    }
    @Test
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


        try{

            //Paso 3
            String carritoCorrecto = "1";

            Assert.assertEquals( carritoCorrecto,carrito);
            System.out.println("La cantidad esperada es: " + carritoCorrecto);
            System.out.println("La cantidad en el carrito obtenida es : " + carrito);
        } catch (AssertionError e){
            System.out.println("La cantidad es incorrecta, cantidad obtenida: " + carrito);
        }


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

            Assert.assertEquals( carritoCorrecto,carrito);
            System.out.println("La cantidad esperada es: " + carritoCorrecto);
            System.out.println("La cantidad en el carrito obtenida es : " + carrito);
        } catch (AssertionError e){
            System.out.println("La cantidad es incorrecta, cantidad obtenida: " + carrito);
        }





    }

    @Test
    public void add3Carrito(){

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        buttonAdd.click();

        WebElement buttonAdd2 = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']"));
        buttonAdd2.click();

        WebElement buttonAdd3 = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
        buttonAdd3.click();


        String carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();


        try{

            //Paso 2
            String carritoCorrecto = "3";

            Assert.assertEquals( carritoCorrecto,carrito);
            System.out.println("La cantidad esperada es: " + carritoCorrecto);
            System.out.println("La cantidad en el carrito obtenida es : " + carrito);
        } catch (AssertionError e){
            System.out.println("La cantidad es incorrecta, cantidad obtenida: " + carrito);

        }

        /*
        int i = 0, cantidad = 3, rango = 6;
        int arreglo[] = new int[cantidad];

        arreglo[i] = (int) (Math.random()*rango);

        for(i=1; i<cantidad; i++) {
            arreglo[i] = (int) (Math.random()*rango);
            for (int j = 0; j<1; j++){
                //Si el valor es repetido retrocede hasta aque el valor sea otro diferente
                if (arreglo[i]==arreglo[j]){
                    i--;
                }
            }
        }

        for (int k=0; k<cantidad ;k++){

            //Para imprimir la cantidad
        }
        */
    }

    @Test
    public void alphabetic(){

        //Paso 1
        WebElement  filtro = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
        filtro.click();
        WebElement  opction = driver.findElement(By.xpath("//option[@value='az']"));
        opction.click();

        WebElement spam = driver.findElement(By.xpath("//span[@class='active_option']"));
        String texto = spam.getAttribute("textContent");

        String textoEsperado = "Name (A to Z)";

        //Paso2

        try{
            Assert.assertEquals(textoEsperado,texto);
            System.out.println("El orden de los productos es el correcto: " + texto);
        }catch (AssertionError e){
            System.out.println("ERROR: El orden de los productos es incorrecto. El texto obtenido es: " + texto );

        }



    }

    @Test
    public void lowHigh(){

        //Paso 1
      WebElement  filtro = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
      filtro.click();
      WebElement  opction = driver.findElement(By.xpath("//option[@value='lohi']"));
      opction.click();

      WebElement spam = driver.findElement(By.xpath("//span[@class='active_option']"));
      String texto = spam.getAttribute("textContent");

      String textoEsperado = "Price (low to high)";

        //Paso2

        try{
            Assert.assertEquals(textoEsperado,texto);
            System.out.println("El orden de los productos es el correcto: " + texto);
        }catch (AssertionError e){
            System.out.println("ERROR: El orden de los productos es incorrecto. El texto obtenido es: " + texto );

        }



    }

    @Test
    public void highLow(){

        //Paso 1
        WebElement  filtro = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
        filtro.click();
        WebElement  opction = driver.findElement(By.xpath("//option[@value='hilo']"));
        opction.click();

        WebElement spam = driver.findElement(By.xpath("//span[@class='active_option']"));
        String texto = spam.getAttribute("textContent");

        String textoEsperado = "Price (high to low)";

        //Paso2

        try{
            Assert.assertEquals(textoEsperado,texto);
            System.out.println("El orden de los productos es el correcto: " + texto);
        }catch (AssertionError e){
            System.out.println("ERROR: El orden de los productos es incorrecto. El texto obtenido es: " + texto );

        }



    }

    @After
    public void tearDown(){

        driver.close();

    }
}


