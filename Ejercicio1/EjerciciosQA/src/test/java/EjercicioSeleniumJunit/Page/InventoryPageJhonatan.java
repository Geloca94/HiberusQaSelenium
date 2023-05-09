package EjercicioSeleniumJunit.Page;

        import java.util.List;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.Select;

public class InventoryPageJhonatan extends AbstractPage {
    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(css = "#inventory_container")
    private WebElement inventoryContainer;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> inventoryNameList;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> inventoryPriceList;
    @FindBy(xpath = "//select[@data-test='product_sort_container']")
    private Select sortSelect;

    public InventoryPageJhonatan(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return inventoryContainer;
    }

    public void selectOption(String option) {
        sortSelect.selectByValue(option);
    }

    public boolean existProductInInventoryList(String itemName) {
        boolean isProductPresent = false;
        for (WebElement webElement: inventoryNameList) {
            if (webElement.getText().equals(itemName)) {
                isProductPresent = true;
                break;
            }
        }
        return isProductPresent;
    }

    public List<WebElement> getInventoryPriceList() {
        return inventoryPriceList;
    }

    public void addItemToCartByName(String itemName) {
        String name = itemName.replace(" ", "-").toLowerCase();
        String xpath = "//button['add-to-cart-" + name + "]";
        WebElement itemElem = getDriver().findElement(By.xpath(xpath));
        itemElem.click();
    }
}