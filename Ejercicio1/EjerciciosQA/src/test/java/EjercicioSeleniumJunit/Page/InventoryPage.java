package EjercicioSeleniumJunit.Page;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class InventoryPage extends AbstractPage {

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartElement;
    @FindBy(className = "inventory_item")
    private WebElement inventoryItemElement;
    @FindBy(className = "product_sort_container")
    private WebElement productSortContainerSelect;
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


    public void addItemToCartByName(String itemName, String action ) {
        try {
            String itemNameReplaced = itemName.replace(" ","-");
            String itemNameLowerCase = itemNameReplaced.toLowerCase();
            WebElement addItemButton = getDriver().findElement(By.xpath("//div[@class='inventory_item_price']/button[@id='"+ action +"-" + itemNameLowerCase + "']"));
            addItemButton.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout adding item " + itemName + " to cart: " + timeoutException.getClass().getSimpleName());
        }
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


}
