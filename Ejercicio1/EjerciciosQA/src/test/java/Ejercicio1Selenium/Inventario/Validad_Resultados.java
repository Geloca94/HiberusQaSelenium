package Ejercicio1Selenium.Inventario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class Validad_Resultados {


    /*

    * Realizar el Login Incorrectamente:
    1. Ir a la pagina https://www.saucedemo.com/
    2. Escribir username Standard_use (introducirlo mal para forzar error)
    3. Escribir password secret sauce
    4. Pulsar Boton Login
    5. Validar que el numero de productos mostrados es igual a 6

     */

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

        List<WebElement> elementos = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        int cantidad = elementos.size();

        if (cantidad != 6){
            System.out.println("No hay 6 objetos en el inventario");

        }else {
            System.out.println("Hay 6 objetis en el inventario");
        }

        driver.close();
    }

}
