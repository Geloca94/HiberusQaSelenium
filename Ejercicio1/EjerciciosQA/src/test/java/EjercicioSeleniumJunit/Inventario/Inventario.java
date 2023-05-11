package EjercicioSeleniumJunit.Inventario;

import EjercicioSeleniumJunit.Pages.InventoryPage;
import EjercicioSeleniumJunit.Pages.LoginPage;
import EjercicioSeleniumJunit.Pages.PagesFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class Inventario {



    WebDriver driver;
    String url = "https://www.saucedemo.com/";
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

    @Test
    public void validationNumeroResultados(){


            int cantidadEsperada = 6;

            //Funcion que te cuenta el tamaño
            Assert.assertEquals("Error la cantidad no corresponde con lo espereado: ", cantidadEsperada,inventoryPage.contarCantidadLista());

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
    // Carrito
    @Test
    public void addCartRandom(){

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
    public void AddTshirtToCart(){

        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", add);
        String expectedCartNumber = "1";
        Assert.assertEquals("ERROR: number of products in cart does not match the expected number", expectedCartNumber, inventoryPage.getNumbInBadge());
    }

    public void DeleteTshirtToCart(){

        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", add);

        inventoryPage.addOrRemoveItemToCartByName("Sauce Labs Bolt T-Shirt", remove);

        WebElement producto =  driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));

        Assert.assertEquals("ERROR: El producto no fue borrado", producto, inventoryPage.getNumbInBadge());
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


