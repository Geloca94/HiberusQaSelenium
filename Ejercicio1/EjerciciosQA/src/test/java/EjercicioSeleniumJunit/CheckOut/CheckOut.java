package EjercicioSeleniumJunit.CheckOut;

import EjercicioSeleniumJunit.Login.LoginAceso;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckOut {




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
    public void ComprobarPedido(){


        //Saco un numero random con Set
        Random rand = new Random();
        Set<Integer> set = new HashSet<Integer>();

        while (set.size() < 3) {
            int randomNumber = rand.nextInt(6) + 1;
            if (!set.contains(randomNumber)) {
                set.add(randomNumber);
            }
        }

        //Almaceno el numero ramdon en 3 variables para realizar la compra
        int item1 = 0, item2 = 0, item3 = 0;
        int i = 1;
        for (Integer num : set) {
            if (i == 1) {
                item1 = num;
            } else if (i == 2) {
                item2 = num;
            } else {
                item3 = num;
            }
            i++;
        }

        //Paso 3 Selecciono los items con los 3 numeros random
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item1 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        String precio1 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item1 +"]"+"//div[contains(@class, 'inventory_item_price')]")).getText();
        buttonAdd.click();

        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item2 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        String precio2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item2 +"]"+"//div[contains(@class, 'inventory_item_price')]")).getText();
        buttonAdd2.click();

        WebElement buttonAdd3 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item3 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        String precio3 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item3 +"]"+"//div[contains(@class, 'inventory_item_price')]")).getText();
        buttonAdd3.click();


        //Paso 4 los convierto en Double
        double num1 = Double.parseDouble(precio1.replace("$",""));
        double num2 = Double.parseDouble(precio2.replace("$",""));
        double num3 = Double.parseDouble(precio3.replace("$",""));

        double total = num1+num2+num3;

        //Paso 5
        WebElement carritolink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        carritolink.click();


        //paso 7
        WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkout.click();

        //paso 8 Rellenar Datos
        WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
        firstName.sendKeys("El PEPE");
        WebElement lastName = driver.findElement(By.xpath("//input[@id='last-name']"));
        lastName.sendKeys("Luis");
        WebElement postal = driver.findElement(By.xpath("//input[@id='postal-code']"));
        postal.sendKeys("33025");

        //paso 9 Continuar
        WebElement continuar = driver.findElement(By.xpath("//input[@id='continue']"));
        continuar.click();

        String subTotalCompra = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();
        double subTotalDouble = Double.parseDouble(subTotalCompra.replace("Item total: $",""));


        try{

            Assert.assertEquals( subTotalDouble,total);
            System.out.println("El precio esperado debe ser: " + subTotalDouble);
            System.out.println("El precio obtenido es: " + total);
            System.out.println("El precio es el correcto");
        } catch (AssertionError e){
            System.out.println("El precio obtenido es incorrecto " + total);
            System.out.println("El precio esperado era: " + subTotalDouble);
        }



    }

    @Test
    public void realizarPedido(){

        //Paso 1
        Random rand = new Random();
        int random = rand.nextInt(6) + 1;

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +random +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd.click();


        //Paso 2
        WebElement carritolink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        carritolink.click();


        //paso 3
        WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkout.click();

        //paso 4 Rellenar Datos
        WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
        firstName.sendKeys("El PEPE");
        WebElement lastName = driver.findElement(By.xpath("//input[@id='last-name']"));
        lastName.sendKeys("Luis");
        WebElement postal = driver.findElement(By.xpath("//input[@id='postal-code']"));
        postal.sendKeys("33025");

        //paso 5 Continuar
        WebElement continuar = driver.findElement(By.xpath("//input[@id='continue']"));
        continuar.click();

        //Paso 6 Finalizar compra
        WebElement finalizar = driver.findElement(By.xpath("//button[@id='finish']"));
        finalizar.click();

        //Paso 7 obtener texto
        String completo = driver.findElement(By.xpath("//div[@class='complete-text']")).getText();

        try{

            //Paso 8 Comprobar Texto
            String textoEsperado = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

            Assert.assertEquals( textoEsperado,completo);
            System.out.println("El texto esperado debe ser: " + textoEsperado);
            System.out.println("El texto obtenido es: " + completo);
        } catch (AssertionError e){
            System.out.println("El texto obtenido es incorrecto " + completo);
        }



    }


    @After
    public void tearDown(){

        driver.close();

    }


}
