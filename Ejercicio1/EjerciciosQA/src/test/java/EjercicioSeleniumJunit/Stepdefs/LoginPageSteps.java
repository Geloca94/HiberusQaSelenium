package EjercicioSeleniumJunit.Stepdefs;

import EjercicioSeleniumJunit.Pages.InventoryPage;
import EjercicioSeleniumJunit.Pages.LoginPage;
import EjercicioSeleniumJunit.Pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class LoginPageSteps {

    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();



    @Given("the user is on home page")
    public void theUserIsOnHomePage() {
        log.info("The user is on the home page");
        loginPage.navigateTo(LoginPage.PAGE_URL);
    }

    @And("the user provide the username {string} and password {string}")
    public void theUserProvideTheUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user is logged successfully and is into the inventory page")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventoryPage() {
        Assert.assertEquals("the URL is not inventory Page",
                InventoryPage.PAGE_URL, PagesFactory.getInstance().getDriver().getCurrentUrl());
    }

    @Then("the user should id be shown and invalid message")
    public void theUserShouldIdBeShownAndInvalidMessage() {

            Assert.assertTrue(loginPage.hasUsernamePasswordError());
    }
}
