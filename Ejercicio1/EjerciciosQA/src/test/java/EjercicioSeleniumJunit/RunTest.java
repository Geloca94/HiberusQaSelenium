package EjercicioSeleniumJunit;


import EjercicioSeleniumJunit.Login.Login;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Login.class
})
public class RunTest {
}
