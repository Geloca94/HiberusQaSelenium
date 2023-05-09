package EjercicioSeleniumJunit.Inventario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class Inventario {



    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com/";



    @Before
    public void setUp(){


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
    public void deleteProductCarrito() {
        // Step 1 - Agregar al carrito 2 productos elegidos al azar
        List <WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@data-test, 'add-to-cart-')]"));
        Random random = new Random();
        int random1 = random.nextInt(addToCartButtons.size());
        int random2;
        do {
            random2 = random.nextInt(addToCartButtons.size());
        }while (random1 == random2);
        addToCartButtons.get(random1).click();
        addToCartButtons.get(random2).click();

        // Step 2 - Ir al carrito
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        List <WebElement> productosCarritoAntes = driver.findElements(By.xpath("//div[@class='cart_item']"));
        // Step 3 - Eliminar uno de los productos
        String numProducts = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        int numeroProductos = Integer.parseInt(numProducts);
        int randomProduct = random.nextInt(numeroProductos);
        List <WebElement> productsInCart = driver.findElements(By.xpath("//button[contains(@data-test, 'remove-')]"));
        productsInCart.get(randomProduct).click();
        // Step 4- Validar que el producto eliminado no aparece en el carrito.
        List <WebElement> productosCarritoDespues = driver.findElements(By.xpath("//div[@class='cart_item']"));
        Assert.assertNotEquals("ERROR: El producto no se ha eliminado del carrito.", productosCarritoAntes.size(), productosCarritoDespues.size());
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


