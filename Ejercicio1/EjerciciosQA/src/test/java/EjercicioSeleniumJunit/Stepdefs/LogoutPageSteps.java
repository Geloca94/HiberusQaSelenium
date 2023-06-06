package EjercicioSeleniumJunit.Stepdefs;

import EjercicioSeleniumJunit.Pages.InventoryPage;
import EjercicioSeleniumJunit.Pages.LoginPage;
import EjercicioSeleniumJunit.Pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import static EjercicioSeleniumJunit.Support.Hooks.driver;


@Slf4j
public class LogoutPageSteps {
    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();

    @When("the user clicks on the react-burger-menu-btn and clicks on the Logout button")
    public void theUserClicksOnTheReactBurgerMenuBtnAndClicksOnTheLogoutButton() throws InterruptedException {
        inventoryPage.clickMenuOut();
    }

    @Then("the user verifies that they are on the home page")
    public void theUserVerifiesThatTheyAreOnTheHomePage() {
        Assert.assertEquals("ERROR: La pagina no corresponde con la esperada",
                LoginPage.PAGE_URL, driver.getCurrentUrl());
    }
}
