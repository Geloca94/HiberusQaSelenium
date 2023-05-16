package EjercicioSeleniumJunit.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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




    public double tranformarStringDouble (){

        double subTotalDouble = 0.0;

        try{
            String subTotalCompra = itemTotalElement.getText();
             subTotalDouble = Double.parseDouble(subTotalCompra.replace("Item total: $",""));

        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking Finish: " + timeoutException.getClass().getSimpleName());
        }


        return subTotalDouble;
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
