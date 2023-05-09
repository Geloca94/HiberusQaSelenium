package EjercicioSeleniumJunit.Page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
@Slf4j
public class CartPage extends AbstractPage{

    //Elementos
    //CheckoutButton
    //removeButton
    //continueShoppingButton
    //openMenu
    //itemsList
    //Metodos
    //ClickCheckout()
    //GetItemCount()
    //ClickcontinueShopping()

    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    @FindBy(xpath = "//button[contains(@name, 'remove-')]")
    private WebElement removeButton;
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;
    @FindBy(xpath = "//div[@class='cart_item']")
    private List<WebElement> itemList;

    CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return checkoutButton;
    }


    public void clickCheckOut(){
        log.info("checkOut in...");
        try{
            checkoutButton.click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking CheckOut: " + timeoutException.getClass().getSimpleName());
        }

    }

    public void getItemCount(){

        try {
            itemList.size();
        }catch(TimeoutException timeoutException){
            log.info("Timeout count items: " + timeoutException.getClass().getSimpleName());
        }



    }

    public void clickContinueShopping(){
        log.info("Continue in...");
        try{
            continueShoppingButton.click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking continue: " + timeoutException.getClass().getSimpleName());
        }
    }
}
