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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static EjercicioSeleniumJunit.Support.Hooks.driver;

public class CheckOutPageSteps {

    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();
    CheckOutStepOnePage checkOutStepOnePage = PagesFactory.getInstance().getCheckOutStepOnePage();
    CheckOutStepSecondPage checkOutStepSecondPage = PagesFactory.getInstance().getCheckOutStepSecondPage();
    CartPage cartPage = PagesFactory.getInstance().getCartPage();

    private double total;

   /* @Given("the inventor page is displayed")
    public void theInventorPageIsDisplayed() {

        Assert.assertEquals("The URL is not inventory Page",
                InventoryPage.PAGE_URL, PagesFactory.getInstance().getDriver().getCurrentUrl());
    }*/

    @And("the user clicks on the add to cart button of product")
    public void theUserClicksOnTheAddToCartButtonOfProduct() {
        int []numerosAleatorios = InventoryPage.generarNumerosAleatoriosUnicos(3);

        // Almacenar el numero de la posicion que quieres extraer de la funcion
        int item1= numerosAleatorios[0];
        int item2= numerosAleatorios[1];
        int item3= numerosAleatorios[2];

        //Paso 3 Selecciono los items con los 3 numeros random
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item1 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        String precio1 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item1 +"]"+"//div[contains(@class, 'inventory_item_price')]")).getText();
        buttonAdd.click();

        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item2 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        String precio2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item2 +"]"+"//div[contains(@class, 'inventory_item_price')]")).getText();
        buttonAdd2.click();

        WebElement buttonAdd3 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item3 +"]"+"//button[contains(@name, 'add-to-cart')]"));
        String precio3 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item3 +"]"+"//div[contains(@class, 'inventory_item_price')]")).getText();
        buttonAdd3.click();


        //Paso 4 los convierto en Double
        double num1 = Double.parseDouble(precio1.replace("$",""));
        double num2 = Double.parseDouble(precio2.replace("$",""));
        double num3 = Double.parseDouble(precio3.replace("$",""));

         total = num1+num2+num3;

    }

    @And("the user clicks on the cart button")
    public void theUserClicksOnTheCartButton() {
        inventoryPage.clickCartLink();

    }

    @And("the user clicks on the CheckOut button")
    public void theUserClicksOnTheCheckOutButton() {

        cartPage.clickCheckOut();

    }

    @And("the user provides the {string} , {string} ..., {string} ..")
    public void theUserProvidesThe(String firstName, String lastName, String ZipCode) {
        checkOutStepOnePage.enterFirstName(firstName);
        checkOutStepOnePage.enterLastName(lastName);
        checkOutStepOnePage.enterPostalCode(ZipCode);
    }

    @When("the user clicks on the Continue button")
    public void theUserClicksOnTheContinueButton() {
        checkOutStepOnePage.clicContinue();

    }

    @Then("the user verifies that the total price of x products equals the sum of their prices")
    public void theUserVerifiesThatTheTotalPriceOfXProductsEqualsTheSumOfTheirPrices() {
        double subTotalDouble = checkOutStepSecondPage.tranformarStringDouble();


        Assert.assertEquals("ERROR: EL PRECIO ES INCORRECTO ", subTotalDouble,total, 0);

    }


    @When("the user clicks on the Finish button")
    public void theUserClicksOnTheFinishButton() {
        checkOutStepSecondPage.clickFinish();

    }

    @Then("the user verifies that a text Thank you for your order! and a BackHome button are displayed")
    public void theUserVerifiesThatATextThankYouForYourOrderAndABackHomeButtonAreDisplayed() {
        String completo = driver.findElement(By.xpath("//div[@class='complete-text']")).getText();

        String textoEsperado = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        Assert.assertEquals("ERROR: NO COINCIDE ", textoEsperado,completo);
    }
}
