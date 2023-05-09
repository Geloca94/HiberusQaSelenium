package EjercicioSeleniumJunit.Page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(xpath = "//h3[@data-text='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement(){
        return loginButton;
    }


    public void clickLogin(){
        log.info("Logging in...");
        try{
            loginButton.click();
        }catch (TimeoutException timeoutException){
            log.info("Timeout clicking login: " + timeoutException.getClass().getSimpleName());
        }
    }

    public void enterUsername(String username){
        usernameInput.click();
        usernameInput.sendKeys(username);
    }

    public void passwordInput(String password){
        usernameInput.click();
        usernameInput.sendKeys(password);
    }

    public boolean hasUsernamePasswordError(){
        return errorMessage.isDisplayed();
    }

}
