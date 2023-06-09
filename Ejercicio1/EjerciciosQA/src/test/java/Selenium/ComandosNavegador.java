package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ComandosNavegador {


    public static void main(String[] arg) throws InterruptedException{

       //PASO 1 levantar el driver
        WebDriver driver ;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();

        //PASO 2 acceso a la pagina
        driver.get("https://www.saucedemo.com/");

        //PASO 3
        String titulo = driver.getTitle();

        //PASO 4
        String titulotamano = String.valueOf(driver.getTitle().length());

        //PASO 5
        String url = driver.getCurrentUrl();
        String codigoFuente = driver.getPageSource();

        String urlPagina = "https://www.saucedemo.com/";

        //PASO 6

        int TamanoPagina = codigoFuente.length();



        System.out.println("El titulo es " + titulo);
        System.out.println("-------");
        System.out.println("El tamaño del titulo es de " + titulotamano);
        System.out.println("-------");
        System.out.println("La url es " + url);
        System.out.println("-------");
        if(urlPagina.equals(url)){
            System.out.println("la URL es Correcta");
        }else {
            System.out.println("la URL es Incorrecta");
        }
        System.out.println("---------");
        System.out.println("El tamaño del codigo fuente es " + TamanoPagina);



        //Thread.sleep(  5000 );

        driver.close();


    }

}
