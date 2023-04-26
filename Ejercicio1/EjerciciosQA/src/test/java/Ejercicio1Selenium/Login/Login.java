package Ejercicio1Selenium.Login;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Login {




    public static void main(String[] arg) throws InterruptedException{
// Paso Inicial
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

/*
* Realizar Login:
1. Ir a la página https://www.saucedemo.com/
2. Escribir el username standard_user
3. Escribir el password secret_sauce
4. Pulsar en el botón del Login.
5. Validar que hemos accedido correctamente a la página, comprobando que se muestra la URL https://www.saucedemo.com/inventory.html
* */

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
        String actualUrl = driver.getCurrentUrl();

        if("https://www.saucedemo.com/inventory.html".equals(actualUrl)) {
            System.out.println("La URL actual y la esperada son correctas. " + "URL Actual = " + actualUrl +
                    " URL Esperada = https://www.saucedemo.com/inventory.html");
        } else {
            System.out.println("Error detectado, La url actual no corresponde con la esperada: "+ "URL Actual = " + actualUrl +
                    " URL Esperada = https://www.saucedemo.com/inventory.html");
        }

        driver.close();
    }
}
