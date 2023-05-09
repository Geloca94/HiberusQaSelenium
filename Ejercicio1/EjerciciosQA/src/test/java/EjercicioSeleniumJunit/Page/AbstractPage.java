package EjercicioSeleniumJunit.Page;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class  AbstractPage {
    private final WebDriver driver;
    protected Wait<WebDriver> wait;

    AbstractPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 500);
    }

    protected WebDriver getDriver(){
        return driver;
    }

    protected boolean isPageLoaded(WebElement element){
        boolean isLoaded = false;
        try {
            isLoaded = element.isDisplayed();
        }catch (NoSuchElementException elementException){
            elementException.printStackTrace();
        }
            return  isLoaded;
    }

    public abstract WebElement getPageLoadedTestElement();
}
