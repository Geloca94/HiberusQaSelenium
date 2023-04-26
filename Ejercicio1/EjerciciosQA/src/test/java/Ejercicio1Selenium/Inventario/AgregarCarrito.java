package Ejercicio1Selenium.Inventario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AgregarCarrito {


    public static void main(String[] arg) throws InterruptedException{
// Paso Inicial
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();


// Step 1
        driver.get("https://www.saucedemo.com/");

// Step 2

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

// Step 3
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

// Step 4
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

// Step 5
        WebElement buttonAdd = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        buttonAdd.click();

// Step 6

        String carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();

        String carritoCorrecto = "1";

        if (carrito.equals(carritoCorrecto)){
            System.out.println("El Carrito el correcto");
        }else {
            System.out.println("El carrito es incorrecto");
        }


        driver.close();
    }

}
