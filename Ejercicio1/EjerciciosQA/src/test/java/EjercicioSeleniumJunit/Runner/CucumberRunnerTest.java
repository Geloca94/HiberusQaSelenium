package EjercicioSeleniumJunit.Runner;


import EjercicioSeleniumJunit.Pages.PagesFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {
                "EjercicioSeleniumJunit.Stepdefs",
                "EjercicioSeleniumJunit.Support"
        },
        features = {
                "src/test/resources"
        }
)
public class CucumberRunnerTest {



}
