package EjercicioSeleniumJunit.Page;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

@Slf4j
public class InventoryPage extends AbstractPage {

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartElement;
    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItemElement;
    @FindBy(className = "inventoryItemElement")
    private List<WebElement> listaInventory;
    @FindBy(className = "product_sort_container")
    private WebElement productSortContainerSelect;
    @FindBy(xpath = "//select[@class= 'product_sort_container']/option")
    private List<WebElement> options;
    @FindBy(xpath = "//div[@class='inventory_item_")
    private List<WebElement> productoPrecios;



    InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return shoppingCartElement;
    }


    public void addOrRemoveItemToCartByName(String itemName, String action ) {
        try {
            String itemNameReplaced = itemName.replace(" ","-");
            String itemNameLowerCase = itemNameReplaced.toLowerCase();
            WebElement addItemButton = getDriver().findElement(By.xpath("//button[@id='"+ action +"-" + itemNameLowerCase + "']"));
            addItemButton.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout adding item " + itemName + " to cart: " + timeoutException.getClass().getSimpleName());
        }
    }

    public String getNumbInBadge(){

        String cantidadCarrito= null;
        try {
        cantidadCarrito = shoppingCartBadge.getText();
        } catch (NoSuchElementException noSuchElementException) {
            log.info("ERROR: WebElement itemsInCartSpan was not found");
        }
        return cantidadCarrito;
    }


    public int contarCantidadLista( ) {

        int cantidad = inventoryItemElement.size();

        return cantidad;

    }



    public void removeItemToCartByName(String itemName) {
        try {
            String itemNameReplaced = itemName.replace(" ","-");
            String itemNameLowerCase = itemNameReplaced.toLowerCase();
            WebElement removeItemButton = getDriver().findElement(By.xpath("//div[@class='inventory_item_price']/button[@id='remove-to-cart-" + itemNameLowerCase + "']"));
            removeItemButton.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout remove item " + itemName + " to cart: " + timeoutException.getClass().getSimpleName());
        }
    }

    public void clickOnShoppingCart(){
        log.info("ShopiingCart in...");
        try{
            shoppingCartElement.click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking ShoppingCart: " + timeoutException.getClass().getSimpleName());
        }
    }

    //Funciones para Ordenar el Inventario y Almacenarlo
    public void sortInventoryByNameAsc(){
        log.info("Ascendant Click...");
        try{
            productSortContainerSelect.click();
            options.get(0).click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking Name Ascendant: " + timeoutException.getClass().getSimpleName());
        }

    }

    public void sortInventoryByNameDesc(){
        log.info("Name Descendant Click...");
        try{
            productSortContainerSelect.click();
            options.get(1).click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking Name Descendant: " + timeoutException.getClass().getSimpleName());
        }

    }

    public void sortInventoryByPriceAsc(){
        log.info("Price Ascendant Click...");
        try{
            productSortContainerSelect.click();
            options.get(2).click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking Price Ascendant: " + timeoutException.getClass().getSimpleName());
        }

    }

    public void sortInventoryByPriceDesc(){
        log.info("Price Descendant Click...");
        try{
            productSortContainerSelect.click();
            options.get(3).click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking Price PriceDescendant: " + timeoutException.getClass().getSimpleName());
        }

    }

    public List<WebElement> selectLista(String option){

        List<WebElement> producto = getDriver().findElements(By.xpath("//div[@class='inventory_item_" + option + "']"));

        return producto;
    }

    public List<Double> almacenListaPrecio(List<WebElement> producto){

        List<Double> productPriceValues = new ArrayList<Double>();

        for (WebElement product: producto) {
            productPriceValues.add(Double.parseDouble(product.getText().substring(1)));
        }

        return productPriceValues;
    }

    public List<String> almacenListaNombre(List<WebElement> producto){

        List<String> stringName = new ArrayList<String>();

        for (WebElement product: producto) {
            stringName.add(product.getText());
        }


        return stringName;
    }

    public static int[] generarNumerosAleatoriosUnicos(int cantidad) {
        int[] numerosAleatorios = new int[cantidad];
        Set<Integer> numerosSet = new HashSet<>();
        Random random = new Random();

        while (numerosSet.size() < cantidad) {
            numerosSet.add(random.nextInt());
        }

        int i = 0;
        for (int numero : numerosSet) {
            numerosAleatorios[i++] = numero;
        }

        return numerosAleatorios;
    }




}
