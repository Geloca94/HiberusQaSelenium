package EjercicioSeleniumJunit.Stepdefs;

import EjercicioSeleniumJunit.Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static EjercicioSeleniumJunit.Support.Hooks.driver;

@Slf4j
public class CarritoPageSteps {


    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();

    String carrito;
    String carritoCorrecto;

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        log.info("The user is on the home page");
        inventoryPage.navigateTo(InventoryPage.PAGE_URL);
    }


    @And("the user clicks on the add to cart")
    public void theUserClicksOnTheAddToCart() {
        int []numerosAleatorios = InventoryPage.generarNumerosAleatoriosUnicos(2);
        // Almacenar el numero de la posicion que quieres extraer de la funcion
        int num1= numerosAleatorios[0];
        int num2= numerosAleatorios[1];;

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num1 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd.click();
        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num2 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        buttonAdd2.click();
    }

    @And("the user see if the number cart are correct")
    public void theUserSeeIfTheNumberCartAreCorrect() {

        carrito = inventoryPage.getNumbInBadge();


        carritoCorrecto = "2";
        Assert.assertEquals("ERROR: La cantidad es incorrecta, cantidad obtenida:" ,carritoCorrecto,carrito);
    }
    @When("the user clicks on the Remove button of x products")
    public void theUserClicksOnTheRemoveButtonOfXProducts() {

        int []posicionAleatoria = InventoryPage.generarNumerosAleatoriosEntre1y2(1);
        int pos1= posicionAleatoria[0];


        WebElement remove = driver.findElement(By.xpath("//div[@class='cart_item_label']"+"[" +pos1 +"]"+"//button[contains(@name, 'remove')]"));
        remove.click();
    }

    @Then("the user verifies that the products no longer appear in the cart")
    public void theUserVerifiesThatTheProductsNoLongerAppearInTheCart() {
        carrito =inventoryPage.getNumbInBadge();



       carritoCorrecto = "1";

        Assert.assertEquals("ERROR: La cantidad es incorrecta, cantidad obtenida: ", carritoCorrecto,carrito);
    }


}
