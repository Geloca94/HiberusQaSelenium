package Selenium;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Pruebas {



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




    }

    @Test
    public void ventanaEmergernte(){

        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/alerts");


        //actions.dragAndDrop().perform();

        boolean isWebElementPresent;

        try{
            isWebElementPresent = driver.findElement(By.cssSelector("#ClickMessage")).isDisplayed();
        }catch (NoSuchElementException nsee){
            isWebElementPresent = false;
        }

        Assert.assertTrue("Elemento no presente", isWebElementPresent);


    }

    @Test
    public void pruebaDragAndDrop(){

        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/droppable");

        WebElement draggable = driver.findElement(By.cssSelector("#draggable"));
        WebElement droppable = driver.findElement(By.cssSelector("#draggable"));

        actions.dragAndDrop(draggable,droppable).perform();

        Assert.assertEquals("Error - No se droppeo correctamente", "dDropped!", draggable.getText());


    }

    @Test
    public void pruebaMouseHover(){

        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/menu");



        WebElement Item2 = driver.findElement(By.xpath("//a[contains(text(),'Main Item 2')]"));
        WebElement subSubList = driver.findElement(By.xpath("//a[contains(text(),'SUB SUB LIST')]"));
        WebElement subSubItem2 = driver.findElement(By.xpath("//a[contains(text(),'Sub Sub Item 2')]"));

        actions.moveToElement(Item2).perform();
        actions.moveToElement(subSubList).perform();
        actions.moveToElement(subSubItem2).perform();

        String expectedTest = "Sub Sub Item 2";

        Assert.assertEquals("ERROR", expectedTest, subSubItem2.getText());

    }

    @Test
    public void pruebaMouseHover2(){

        Actions actions = new Actions(driver);

        driver.get("https://the-internet.herokuapp.com/hovers");



        WebElement imagen = driver.findElement(By.xpath("//div[@class='figure'][3]"));
        actions.moveToElement(imagen).perform();

        WebElement figura = driver.findElement(By.xpath("//a[contains(text(),'View profile')]"));

        actions.moveToElement(figura).perform();
        actions.click(figura);

        String paginaActual = driver.getCurrentUrl();
        String expectedPagina = "https://the-internet.herokuapp.com/users/3";

        Assert.assertEquals("ERROR", expectedPagina,paginaActual);

    }
    @After
    public void tearDown(){
        driver.close();
    }

}
