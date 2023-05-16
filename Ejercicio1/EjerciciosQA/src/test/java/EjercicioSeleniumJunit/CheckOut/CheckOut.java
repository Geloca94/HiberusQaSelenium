package EjercicioSeleniumJunit.CheckOut;

import EjercicioSeleniumJunit.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckOut {



    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com/";

    InventoryPage inventoryPage;
    CheckOutStepOnePage checkOutStepOnePage;
    CheckOutStepSecondPage checkOutStepSecondPage;
    CartPage cartPage;
    LoginPage loginPage;

    @Before
    public void setUp(){


        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 5);


        PagesFactory.start(driver);
        driver.get(InventoryPage.PAGE_URL);
        PagesFactory pagesFactory = PagesFactory.getInstance();


        inventoryPage = pagesFactory.getInventoryPage();
        checkOutStepOnePage = pagesFactory.getCheckOutStepOnePage();
        checkOutStepSecondPage = pagesFactory.getCheckOutStepSecondPage();
        cartPage = pagesFactory.getCartPage();

        loginPage = pagesFactory.getLoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();




    }


    @Test
    public void ComprobarPedido(){

        int []numerosAleatorios = InventoryPage.generarNumerosAleatoriosUnicos(3);

        // Almacenar el numero de la posicion que quieres extraer de la funcion
        int item1= numerosAleatorios[0];
        int item2= numerosAleatorios[1];
        int item3= numerosAleatorios[2];

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
       inventoryPage.clickCartLink();


        //paso 7
        cartPage.clickCheckOut();

        //paso 8 Rellenar Datos

        checkOutStepOnePage.enterFirstName("PEPE");
        checkOutStepOnePage.enterLastName("Luis");
        checkOutStepOnePage.enterPostalCode("9999");



        //paso 9 Continuar
        checkOutStepOnePage.clicContinue();

        double subTotalDouble = checkOutStepSecondPage.tranformarStringDouble();


            Assert.assertEquals("ERROR: EL PRECIO ES INCORRECTO ", subTotalDouble,total, 0);

    }

    @Test
    public void realizarPedido(){

        int []numerosAleatorios = InventoryPage.generarNumerosAleatoriosUnicos(3);

        // Almacenar el numero de la posicion que quieres extraer de la funcion
        int item1= numerosAleatorios[0];
        int item2= numerosAleatorios[1];
        int item3= numerosAleatorios[2];

        //Paso 3 Selecciono los items con los 3 numeros random
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item1 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd.click();

        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item2 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd2.click();

        WebElement buttonAdd3 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item3 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd3.click();


        //Paso 2
        inventoryPage.clickCartLink();


        //paso 3
        cartPage.clickCheckOut();

        //paso 4 Rellenar Datos
        checkOutStepOnePage.enterFirstName("PEPE");
        checkOutStepOnePage.enterLastName("Luis");
        checkOutStepOnePage.enterPostalCode("9999");

        //paso 5 Continuar
        checkOutStepOnePage.clicContinue();

        //Paso 6 Finalizar compra
        checkOutStepSecondPage.clickFinish();

        //Paso 7 obtener texto
        String completo = driver.findElement(By.xpath("//div[@class='complete-text']")).getText();

        //Paso 8 Comprobar Texto
        String textoEsperado = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        Assert.assertEquals("ERROR: NO COINCIDE ", textoEsperado,completo);

    }


    @After
    public void tearDown(){

        driver.close();

    }


}
