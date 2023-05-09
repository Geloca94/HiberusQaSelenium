package EjercicioSeleniumJunit.Carrito;

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
        int item1 = 0, item2 = 0;
        int i = 1;
        for (Integer num : set) {
            if (i == 1) {
                item1 = num;
            } else {
                item2 = num;
            }
            i++;
        }

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item1 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd.click();
        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item2 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd2.click();

       // List <WebElement> carritoVacio = driver.findElements();

        //Paso 2
        WebElement carritolink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        carritolink.click();

        String carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();

        //Paso 3
        String carritoCorrectoAntes = "2";
        Assert.assertEquals("ERROR: La cantidad es incorrecta, cantidad obtenida:" ,carritoCorrectoAntes,carrito);


        //paso 4
        WebElement remove = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item1 +"]"+"//button[contains(@name, 'remove')]"));
        remove.click();

        //List<WebElement> nombreProducto = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();


        //Paso 5
        String carritoCorrectoDespues = "1";

        Assert.assertEquals("ERROR: La cantidad es incorrecta, cantidad obtenida: ", carritoCorrectoDespues,carrito);




    }


    @After
    public void tearDown(){

        driver.close();

    }
}
