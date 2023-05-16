package EjercicioSeleniumJunit.Carrito;

import EjercicioSeleniumJunit.Pages.InventoryPage;
import EjercicioSeleniumJunit.Pages.LoginPage;
import EjercicioSeleniumJunit.Pages.PagesFactory;
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

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Carrito {

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
    public void carrito(){

        //Saco un numero random con Set
        int []numerosAleatorios = InventoryPage.generarNumerosAleatoriosUnicos(2);
        // Almacenar el numero de la posicion que quieres extraer de la funcion
        int num1= numerosAleatorios[0];
        int num2= numerosAleatorios[1];;

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num1 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd.click();
        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num2 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd2.click();

       // List <WebElement> carritoVacio = driver.findElements();

        //Paso 2
        inventoryPage.clickCartLink();

        String carrito = inventoryPage.getNumbInBadge();

        //Paso 3
        String carritoCorrectoAntes = "2";
        Assert.assertEquals("ERROR: La cantidad es incorrecta, cantidad obtenida:" ,carritoCorrectoAntes,carrito);


        int []posicionAleatoria = InventoryPage.generarNumerosAleatoriosEntre1y2(1);
        int pos1= posicionAleatoria[0];
        //paso 4
        WebElement remove = driver.findElement(By.xpath("//div[@class='cart_item_label']"+"[" +pos1 +"]"+"//button[contains(@name, 'remove')]"));
        remove.click();

        //List<WebElement> nombreProducto = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        carrito =inventoryPage.getNumbInBadge();


        //Paso 5
        String carritoCorrectoDespues = "1";

        Assert.assertEquals("ERROR: La cantidad es incorrecta, cantidad obtenida: ", carritoCorrectoDespues,carrito);





    }


    @After
    public void tearDown(){

        driver.close();

    }
}
