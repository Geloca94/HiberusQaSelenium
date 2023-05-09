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
public class CheckOutStepOnePage extends AbstractPage{

    @FindBy(id = "first-name")
    private WebElement firstNameInput;
    @FindBy(id= "last-name")
    private WebElement lastNameInput;
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;


    CheckOutStepOnePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return continueButton;
    }

    public void enterFirstName(String firstName){
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameInput.click();
        lastNameInput.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode){
        postalCodeInput.click();
        postalCodeInput.sendKeys(postalCode);
    }

    public void clicContinue(){
        log.info("Continue in...");
        try{
            continueButton.click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking continue: " + timeoutException.getClass().getSimpleName());
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
