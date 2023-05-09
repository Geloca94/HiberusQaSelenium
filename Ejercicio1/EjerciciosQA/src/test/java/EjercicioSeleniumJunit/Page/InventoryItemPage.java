package EjercicioSeleniumJunit.Page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class InventoryItemPage extends AbstractPage {


    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;

    @FindBy(id = "inventory_details_name large_size")
    private WebElement itemName;
    @FindBy(id = "inventory_details_desc large_size")
    private WebElement itemDescripcion;
    @FindBy(className = "btn btn_primary btn_small btn_inventory")
    private WebElement addToCartButton;
    @FindBy(tagName = "button", linkText = "remove")
    private WebElement removeButton;
    @FindBy(className = "inventory_details_price")
    private WebElement itemPrice;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartElement;
    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;
    @FindBy(id = "inventory_details_img")
    private WebElement img;



    InventoryItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return itemName;
    }

    public void addToCart() {
        try {

            addToCartButton.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout adding item " + timeoutException.getClass().getSimpleName());
        }
    }

    public void setRemoveButton() {
        try {
            removeButton.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout remove item " + timeoutException.getClass().getSimpleName());
        }
    }
    public boolean checkImage(){
        return img.isDisplayed();
    }


}
