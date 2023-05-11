package EjercicioSeleniumJunit.Inventario;

import EjercicioSeleniumJunit.Page.InventoryPage;
import EjercicioSeleniumJunit.Page.LoginPage;
import EjercicioSeleniumJunit.Page.PagesFactory;
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
    LoginPage loginPage;
    InventoryPage inventoryPage;
    String precio = "price";
    String nombre = "name";
    String add= "add-to-cart";
    String remove =  "remove";


    @Before
    public void setUp(){


        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PagesFactory.start(driver);
        driver.get(InventoryPage.PAGE_URL);
        PagesFactory pagesFactory = PagesFactory.getInstance();

        inventoryPage = pagesFactory.getInventoryPage();


        //Acceso a la PAgina
        loginPage = pagesFactory.getLoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();




    }
    //validar Numero de resultados
    @Test
    public void validationNumeroResultados(){


            int cantidadEsperada = 6;

            //Funcion que te cuenta el tamaño
            Assert.assertEquals("Error la cantidad no corresponde con lo espereado: ", cantidadEsperada,inventoryPage.contarCantidadLista());

    }

    //Validad que un producto exista
    @Test
    public void validationProducto(){



        String camisetaEsperada = "Sauce Labs Bolt T-Shirt";

        boolean isPresent = inventoryPage.porductoCarrito(camisetaEsperada);



        Assert.assertTrue("Error el producto no existe: ", isPresent);

    }
    // Añadir Producto Random al carrito
    @Test
    public void addCartRandom(){


        //Cantidad a introducir de numeros aleatorios
        int []numerosAleatorios = inventoryPage.generarNumerosAleatoriosUnicos(1);

        // Almacenar el numero de la posicion que quieres extraer de la funcion
        int num1= numerosAleatorios[0];

        //Buscar Producto  mediante el driver
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num1 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd.click();


        String expectedCartNumber = "1";
        Assert.assertEquals("ERROR: number of products in cart does not match the expected number", expectedCartNumber, inventoryPage.getNumbInBadge());


    }
    //Añadir Camiseta
    @Test
    public void AddTshirtToCart(){

        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", add);
        String expectedCartNumber = "1";
        Assert.assertEquals("ERROR: number of products in cart does not match the expected number", expectedCartNumber, inventoryPage.getNumbInBadge());
    }
    //Borrar Producto y validad que fue borrado
    @Test
    public void DeleteTshirtToCart(){

        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", add);

        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", remove);


        Assert.assertFalse("ERROR: El producto no fue borrado", InventoryPage.isElementPresent());
    }

    //Borar Producto Carrito
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
    //Añadir 3 productos al carrito
    @Test
    public void add3Carrito(){


        //Cantidad a introducir de numeros aleatorios
        int []numerosAleatorios = inventoryPage.generarNumerosAleatoriosUnicos(3);

        // Almacenar el numero de la posicion que quieres extraer de la funcion
        int num1= numerosAleatorios[0];
        int num2= numerosAleatorios[1];
        int num3= numerosAleatorios[2];

        //Paso 3 Selecciono los items con los 3 numeros random
        WebElement buttonAdd = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num1 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd.click();

        WebElement buttonAdd2 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num2 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd2.click();

        WebElement buttonAdd3 = driver.findElement(By.xpath("//div[@class='inventory_item']"+"[" +num3 +"]"+"//button[contains(@name, 'add-to-car')]"));
        buttonAdd3.click();


        //Paso 4 compruebo que esten  los 3 items

        String expectedCartNumber = "3";
        Assert.assertEquals("ERROR: number of products in cart does not match the expected number", expectedCartNumber, inventoryPage.getNumbInBadge());





    }
    //Ordenar AZ
    @Test
    public void AZ(){

        inventoryPage.sortInventoryByNameAsc();

        //Paso 2 almaceno en un listado de Web Element los productos
        List <WebElement> productoNombres = inventoryPage.selectLista(nombre);

        //Paso 3 Saco en una lista los String


        List<String>productName = inventoryPage.almacenListaNombre(productoNombres);


        //Paso 4 Almaceno la lista buena con la cual voy a comparar
        List<String> nombreAZ = new ArrayList<String>(productName);
        Collections.sort(nombreAZ);


        Assert.assertEquals("ERROR: No esta ordenado", nombreAZ, productName);


    }
    //Ordenar ZA
    @Test
    public void ZA(){

        inventoryPage.sortInventoryByNameDesc();

        //Paso 2 almaceno en un listado de Web Element los productos
        List <WebElement> productoNombres = inventoryPage.selectLista(nombre);

        //Paso 3 Saco en una lista los String


        List<String>productName = inventoryPage.almacenListaNombre(productoNombres);


        //Paso 4 Almaceno la lista buena con la cual voy a comparar
        List<String> nombreZA = new ArrayList<String>(productName);
        Collections.sort(nombreZA, Collections.reverseOrder());


        Assert.assertEquals("ERROR: No esta ordenado", nombreZA, productName);


    }

    //Ordenar Low High
    @Test
    public void lowHigh(){

        //Paso 1 hago click
        inventoryPage.sortInventoryByPriceAsc();

        //Paso 2 selecciono los elementos
         List <WebElement> productoPrecios = inventoryPage.selectLista(precio);

        //Paso 4 Creo una lista vacia para almacenarlos y Los almaceno
        List<Double>productPriceValues = inventoryPage.almacenListaPrecio(productoPrecios);

        //Paso 5 Creo la copia
        List<Double> sortedProductPriceValues = new ArrayList<Double>(productPriceValues);
        Collections.sort(sortedProductPriceValues);

        //Paso 6 Comparo
        Assert.assertEquals("Error: NO COINCIDEN LOS PRECIOS ", sortedProductPriceValues, productPriceValues);

    }
    //Ordenar HighLow
    @Test
    public void highLow(){

        //Paso 1 hago click
        inventoryPage.sortInventoryByPriceDesc();

        //Paso 2 selecciono los elementos
        List <WebElement> productoPrecios = inventoryPage.selectLista(precio);

        //Paso 4 Creo una lista vacia para almacenarlos y Los almaceno
        List<Double>productPriceValues = inventoryPage.almacenListaPrecio(productoPrecios);

        //Paso 5 Creo la copia y le hago el reversa
        List<Double> sortedProductPriceValues = new ArrayList<Double>(productPriceValues);
        Collections.sort(sortedProductPriceValues, Collections.reverseOrder());

        //Paso 6 Comparo
        Assert.assertEquals("Error: NO COINCIDEN LOS PRECIOS ", sortedProductPriceValues, productPriceValues);


    }

    @After
    public void tearDown(){

        driver.close();

    }
}


