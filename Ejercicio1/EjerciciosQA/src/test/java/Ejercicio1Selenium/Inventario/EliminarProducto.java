package Ejercicio1Selenium.Inventario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EliminarProducto {

    public static void main(String[] arg) throws InterruptedException{
// Paso Inicial
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();


        // Step 1 Acceso a la pagina web
        driver.get("https://www.saucedemo.com/");

        // Step 2 Introducion de Username

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        // Step 3 Introduccion de contraseña
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        // Step 4 Apretar Boton Login
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        // Step 5 Agregar al carrito un producto
        WebElement buttonAdd = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        buttonAdd.click();

        // Step 6 Compruebo que el boton aparece

        String remove = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bolt-t-shirt']")).getText();

        String removeCorrecto = "Remove";

        if (remove.equals(removeCorrecto)){
            System.out.println("El boton de remove es el correcto");
        }else {
            System.out.println("El boton de remove es incorrecto");
        }


        driver.close();
    }
}
