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

    //Menu
    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;
    //Link del carrito
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartElement;
    //numero del carrito
    @FindBy(className = "shopping_cart_badge")
    private static WebElement shoppingCartBadge;
    //Item
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItemElement;
    //Nombre del Item
    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventorIemElementName;
    //Ordenar productos
    @FindBy(className = "product_sort_container")
    private WebElement productSortContainerSelect;
    //Opciones de ordenar productos
    @FindBy(xpath = "//select[@class= 'product_sort_container']/option")
    private List<WebElement> options;





    InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return shoppingCartElement;
    }

    /*----------------FUNCIONES AÑADIR O BORRAR-----------------*/
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


    /*----------------FUNCIONES CARRITO-----------------*/

    //FUNCION PARA COGER EL NUMERO QUE SE MARCA EN EL CARRITO
    public String getNumbInBadge(){

        String cantidadCarrito= null;
        try {
        cantidadCarrito = shoppingCartBadge.getText();
        } catch (NoSuchElementException noSuchElementException) {
            log.info("ERROR: WebElement itemsInCartSpan was not found");
        }
        return cantidadCarrito;
    }

    //FUNCION PARA SABER SI EL CARRITO TIENE O NO PRODUCTOS
    public static boolean isElementPresent() {
        try {
            return shoppingCartBadge.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //FUNCION PARA CONTAR LA CANIDAD DE LA LISTA DE INVENTARIO
    public int contarCantidadLista( ) {

        int cantidad = inventoryItemElement.size();

        return cantidad;

    }

    //FUNCION PARA GENERAR NUMERO ALEATORIO
    public static int[] generarNumerosAleatoriosUnicos(int cantidad)  {
        int[] numerosAleatorios = new int[cantidad];
        Set<Integer> numerosSet = new HashSet<>();
        Random random = new Random();

        while (numerosSet.size() < cantidad) {
            //6 + 1 PORQUE SI NO TE COGE DEL 0 A 5
            numerosSet.add(random.nextInt(6) + 1);
        }

        int i = 0;
        for (int numero : numerosSet) {
            numerosAleatorios[i++] = numero;
        }

        return numerosAleatorios;
    }

    //FUNCION PARA COMPROBAR QUE EL PRODUCTO ESTA EN EL CARRITO

    public boolean porductoCarrito(String productoEsperado){
        boolean isPresent = false;

        for(int i = 0; i < inventorIemElementName.size(); i++){

            if( inventorIemElementName.get(i).getText().equals(productoEsperado)){
                isPresent = true;
            }
        }

        return isPresent;
    }


    /*----------------FUNCIONES ORDENAR-----------------*/
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

    //FUNCION PARA SELECCIONAR LA OPCION
    public List<WebElement> selectLista(String option){

        List<WebElement> producto = getDriver().findElements(By.xpath("//div[@class='inventory_item_" + option + "']"));

        return producto;
    }

    //FUNCION PARA ALMACENAR LA LISA DE PRECIOS PARA ORDENAR

    public List<Double> almacenListaPrecio(List<WebElement> producto){

        List<Double> productPriceValues = new ArrayList<Double>();

        for (WebElement product: producto) {
            productPriceValues.add(Double.parseDouble(product.getText().substring(1)));
        }

        return productPriceValues;
    }

    //FUNCION PARA ALMACENAR LA LISA DE NOMBRES PARA ORDENAR
    public List<String> almacenListaNombre(List<WebElement> producto){

        List<String> stringName = new ArrayList<String>();

        for (WebElement product: producto) {
            stringName.add(product.getText());
        }


        return stringName;
    }






}
