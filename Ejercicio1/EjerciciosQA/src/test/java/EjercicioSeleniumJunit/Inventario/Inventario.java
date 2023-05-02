package EjercicioSeleniumJunit.Inventario;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import jdk.nashorn.internal.ir.CatchNode;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import EjercicioSeleniumJunit.Login.LoginAceso;


import java.sql.SQLOutput;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Inventario {


    LoginAceso loginAceso;

    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com/";



    @Before
    public void setUp(){

        loginAceso = new LoginAceso();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 5);


        driver.get(url);

        //loginAceso.Acceso();

        //USER
        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        //CONTRA
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        //BOTON
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();


    }

    @Test
    public void validationNumeroResultados(){

        //Paso 1
        List<WebElement> elementos = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        //Paso 2
            int cantidad = elementos.size();
            int cantidadEsperada = 6;

            Assert.assertEquals("Error la cantidad no corresponde con lo espereado: ", cantidadEsperada,cantidad);





    }
    @Test
    public void validationProducto(){



        String camisetaEsperada = "Sauce Labs Bolt T-Shirt";

        List<WebElement> listaProducto= driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        boolean isPresent = false;

        for(int i = 0; i < listaProducto.size(); i++){

           if( listaProducto.get(i).getText().equals(camisetaEsperada)){
               isPresent = true;
           }
        }

        Assert.assertTrue("El Resultado es ", isPresent);

    }

    @Test
    public void addCarrito(){

        Random rand = new Random();
        int random = rand.nextInt(6)+1;

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +random +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd.click();

        //Paso 2
        String carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();

            //Paso 3
            String carritoCorrecto = "1";

            Assert.assertEquals( "Error, la cantidad no corresponde con el carrito ",carritoCorrecto,carrito);


    }


    @Test
    public void deleteCarrito(){

        //Paso 1
        WebElement buttonAdd = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        buttonAdd.click();

        // Step 6 Compruebo que el boton aparece

        WebElement remove = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bolt-t-shirt']"));
        remove.click();

        try {
            WebElement carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        }catch (NoSuchElementException we) {

            Assert.fail("ERROR, el carrito no esta vacio");
        }





    }

    @Test
    public void add3Carrito(){


        //Saco un numero random con Set
        Random rand = new Random();
        Set<Integer> set = new HashSet<Integer>();

        while (set.size() < 3) {
            int randomNumber = rand.nextInt(6) + 1;
            if (!set.contains(randomNumber)) {
                set.add(randomNumber);
            }
        }

        //Almaceno el numero ramdon en 3 variables para realizar la compra
        int item1 = 0, item2 = 0, item3 = 0;
        int i = 1;
        for (int num : set) {
            if (i == 1) {
                item1 = num;
            } else if (i == 2) {
                item2 = num;
            } else {
                item3 = num;
            }
            i++;
        }

        //Paso 3 Selecciono los items con los 3 numeros random
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item1 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd.click();

        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item2 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd2.click();

        WebElement buttonAdd3 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +item3 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd3.click();


        String carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();



            //Paso 4 compruebo que esten  los 3 items
            String carritoCorrecto = "3";

            Assert.assertEquals( "Error, la cantidad no corresponde con el carrito ",carritoCorrecto,carrito);


        /*

        //Otra manera de generar numeros ramdom
        int i = 0, cantidad = 3, rango = 6;
        int arreglo[] = new int[cantidad];

        arreglo[i] = (int) (Math.random()*rango);

        for(i=1; i<cantidad; i++) {
            arreglo[i] = (int) (Math.random()*rango);
            for (int j = 0; j<1; j++){
                //Si el valor es repetido retrocede hasta aque el valor sea otro diferente
                if (arreglo[i]==arreglo[j]){
                    i--;
                }
            }
        }

        for (int k=0; k<cantidad ;k++){

            //Para imprimir la cantidad
        }
        */
    }

    @Test
    public void alphabetic(){

        //Paso 1 hago click
        driver.findElement(By.xpath("//select[@data-test='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[@value='za']")).click();

        //Paso 2 almaceno en un listado de Web Element los productos
        List<WebElement> nombreProducto = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        //Paso 3 Saco en una lista los String
        List<String> stringNombre = new ArrayList<String>();
        for (WebElement productName : nombreProducto) {
            stringNombre.add(productName.getText());
        }

        //Paso 4 Almaceno la lista buena con la cual voy a comparar
        List<String> nombreCortoProducto = new ArrayList<String>(stringNombre);
        Collections.sort(nombreCortoProducto, Collections.reverseOrder());


        Assert.assertEquals("ERROR: No esta ordenado", nombreCortoProducto, stringNombre);


    }

    @Test
    public void lowHigh(){

        //Paso 1 hago click
        driver.findElement(By.xpath("//select[@data-test='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[@value='lohi']")).click();

        //Paso 2 selecciono los elementos
        List<WebElement> productoPrecios = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        //Paso 3 Creo una lista vacia para almacenar
        List<Double> productPriceValues = new ArrayList<Double>();

        //Paso 4 Los almaceno
        for (WebElement productPrecio : productoPrecios) {
            productPriceValues.add(Double.parseDouble(productPrecio.getText().substring(1)));
        }

        //Paso 5 Creo la copia
        List<Double> sortedProductPriceValues = new ArrayList<Double>(productPriceValues);
        Collections.sort(sortedProductPriceValues);

        //Paso 6 Comparo
        Assert.assertEquals("Error: NO COINCIDEN LOS PRECIOS ", sortedProductPriceValues, productPriceValues);

    }

    @Test
    public void highLow(){

        //Paso 1 hago click
        driver.findElement(By.xpath("//select[@data-test='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[@value='hilo']")).click();

        //Paso 2 selecciono los elementos
        List<WebElement> productoPrecios = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        //Paso 3 Creo una lista vacia para almacenar
        List<Double> productPriceValues = new ArrayList<Double>();

        //Paso 4 Los almaceno
        for (WebElement productPrecio : productoPrecios) {
            productPriceValues.add(Double.parseDouble(productPrecio.getText().substring(1)));
        }

        //Paso 5 Creo la copia y le hago el reversa
        List<Double> copiaValosPrecioProductos = new ArrayList<Double>(productPriceValues);
        Collections.sort(copiaValosPrecioProductos, Collections.reverseOrder());

        //Paso 6 Comparo
        Assert.assertEquals("Error: NO COINCIDEN LOS PRECIOS ", copiaValosPrecioProductos, productPriceValues);


    }

    @After
    public void tearDown(){

        driver.close();

    }
}


