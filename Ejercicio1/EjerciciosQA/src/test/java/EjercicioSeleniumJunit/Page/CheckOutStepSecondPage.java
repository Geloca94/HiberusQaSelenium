package EjercicioSeleniumJunit.Page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class CheckOutStepSecondPage extends AbstractPage {

    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalElement;
    @FindBy(className = "summary_tax_label")
    private WebElement taxElement;
    @FindBy(className = "summary_info_label summary_total_label")
    private WebElement totalElement;
    @FindBy(id = "finish")
    private WebElement finishButton;
    @FindBy(id = "cancel")
    private WebElement cancelButton;



    CheckOutStepSecondPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return finishButton;
    }

    public void getItemtotal(){

         String itemTotalText = itemTotalElement.getText();

        double itemTotalDouble = Double.parseDouble(itemTotalText.replace("Item total: $",""));
    }

    public void getTax(){
        String taxText = taxElement.getText();

        double taxDouble = Double.parseDouble(taxText.replace("Item total: $",""));
    }

    public void getTotal(){

        String TotalText = totalElement.getText();

        double totalDouble = Double.parseDouble(TotalText.replace("Item total: $",""));
    }

    public void clickFinish(){
        log.info("Finish in...");
        try{
            finishButton.click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking Finish: " + timeoutException.getClass().getSimpleName());
        }
    }

    public void clickCancel(){
        log.info("Cancel in...");
        try{
            cancelButton.click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking Cancel: " + timeoutException.getClass().getSimpleName());
        }
    }

}
