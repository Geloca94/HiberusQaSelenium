package EjercicioSeleniumJunit.Pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class PagesFactory {

    private static PagesFactory pagesFactories;

    private final WebDriver driver;
    //Paginas para inicializar
    private final LoginPage loginPage;
    private final CartPage cartPage;
    private final CheckOutStepOnePage checkOutStepOnePage;
    private final CheckOutStepSecondPage checkOutStepSecondPage;
    private final InventoryPage inventoryPage;
    private final InventoryItemPage inventoryItemPage;
    private final InventoryPageJhonatan inventoryPageJhonatan;

    private PagesFactory(WebDriver driver){
        this.driver = driver;
        //Initilizar nuestras Pages
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        checkOutStepOnePage = new CheckOutStepOnePage(driver);
        checkOutStepSecondPage = new CheckOutStepSecondPage(driver);
        inventoryPage = new InventoryPage(driver);
        inventoryItemPage = new InventoryItemPage(driver);
        inventoryPageJhonatan = new InventoryPageJhonatan(driver);
    }

    public static void start(WebDriver driver){
        pagesFactories = new PagesFactory(driver);
    }


    public WebDriver getDriver(){
        return driver;
    }

    public static PagesFactory getInstance(){
        return pagesFactories;
    }



}
