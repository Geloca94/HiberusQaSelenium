package EjercicioSeleniumJunit.Stepdefs;

import EjercicioSeleniumJunit.Pages.InventoryPage;
import EjercicioSeleniumJunit.Pages.LoginPage;
import EjercicioSeleniumJunit.Pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static EjercicioSeleniumJunit.Support.Hooks.driver;
@Slf4j
public class InventarioPageSteps {

    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();
    String add= "add-to-cart";
    String remove =  "remove";
    String nombre = "name";


    @When("the inventory page is displayed")
    public void theInventoryPageIsDisplayed() {
        Assert.assertEquals("The URL is not inventory Page",
                InventoryPage.PAGE_URL, PagesFactory.getInstance().getDriver().getCurrentUrl());

    }

    @Then("the user checks that {string} products are displayed in the inventory.")
    public void theUserChecksThatProductsAreDisplayedInTheInventory(String expectedInventoryNumber) {


        int expectedInventoryNumberInt = Integer.parseInt(expectedInventoryNumber);

        Assert.assertEquals("ERROR: number of products in cart does not match the expected number", expectedInventoryNumberInt, inventoryPage.contarCantidadLista());


    }
    @When("the inventory page is display")
    public void theInventoryPageIsDisplay() {
        Assert.assertEquals("The URL is not inventory Page",
                InventoryPage.PAGE_URL, PagesFactory.getInstance().getDriver().getCurrentUrl());
    }

    @Then("the user checks that the {string} is in the inventory.")
    public void theUserChecksThatTheIsInTheInventory(String product) {

        boolean isPresent = inventoryPage.porductoCarrito(product);


        Assert.assertTrue("Error el producto no existe: ", isPresent);
    }

    @When("the user clicks on the add to cart button for the product Sauce Labs Bolt T-shirt")
    public void theUserClicksOnTheAddToCartButtonForTheProductSauceLabsBoltTShirt() {
        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", add);

    }

    @Then("the user checks that the product is in the cart.")
    public void theUserChecksThatTheProductIsInTheCart() {

        String expectedCartNumber = "1";
        Assert.assertEquals("ERROR: number of products in cart does not match the expected number", expectedCartNumber, inventoryPage.getNumbInBadge());


    }

    @And("the user clicks on the add to cart button")
    public void theUserClicksOnTheAddToCartButton() {
        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", add);

    }


    @And("the user checks thats the product is in the cart")
    public void theUserChecksThatsTheProductIsInTheCart() {

        String expectedCartNumber = "1";
        Assert.assertEquals("ERROR: number of products in cart does not match the expected number", expectedCartNumber, inventoryPage.getNumbInBadge());
    }

    @When("the user clicks on the remove button")
    public void theUserClicksOnTheRemoveButton() {
        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", remove);
    }

    @Then("the user checks that the product is no longer in the cart.")
    public void theUserChecksThatTheProductIsNoLongerInTheCart() {
        Assert.assertFalse("ERROR: El producto no fue borrado", InventoryPage.isElementPresent());
    }

    @When("the user clicks on the add to cart button of x product")
    public void theUserClicksOnTheAddToCartButtonOfXProduct() {

        int []numerosAleatorios = InventoryPage.generarNumerosAleatoriosUnicos(3);

        int num1= numerosAleatorios[0];
        int num2= numerosAleatorios[1];
        int num3= numerosAleatorios[2];

        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num1 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd.click();

        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num2 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd2.click();

        WebElement buttonAdd3 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num3 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd3.click();

    }

    @Then("the user checks that the cart icon displays a number equivalent to the x selected products.")
    public void theUserChecksThatTheCartIconDisplaysANumberEquivalentToTheXSelectedProducts() {

        String expectedCartNumber = "3";
        Assert.assertEquals("ERROR: number of products in cart does not match the expected number", expectedCartNumber, inventoryPage.getNumbInBadge());
    }

    @When("the user locates the product_sort_container")
    public void theUserLocatesTheProduct_sort_container() {

        inventoryPage.sortInventoryByNameDesc();

    }


    @Then("the user selects the ZA option")
    public void theUserSelectsTheZAOption() {

        List<WebElement> productoNombres = inventoryPage.selectLista(nombre);

        List<String>productName = inventoryPage.almacenListaNombre(productoNombres);

        List<String> nombreZA = new ArrayList<String>(productName);
        Collections.sort(nombreZA, Collections.reverseOrder());

        Assert.assertEquals("ERROR: No esta ordenado", nombreZA, productName);
    }


}
