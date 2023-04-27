package Junit;

import org.junit.*;

public class AnotacionesJunit {

    @BeforeClass
    public  static void setUpClass(){
        System.out.println("BEFORE CLASS");
// TODO LO QUE SE ABRA AQUI
    }

    @Before
    public  void setUp(){
        System.out.println("BEFORE");
    }

    @Test
    public  void test1(){
        System.out.println("TEST");
    }

    @Test
    public void testAssertArrayEquals(){
        String[] nombreEsperados = {"java", "Junit", "Jboss"};
        String[] nombreActual = {"java", "Junit", "Jboss"};

        Assert.assertArrayEquals(" No son los mismos Arrays", nombreEsperados, nombreActual);
    }

    @Test
    public  void test2(){
        System.out.println("TEST2");
    }

    @Test
    public  void test3(){
        System.out.println("TEST3");
    }

    @After
    public    void tearDown(){
        System.out.println("AFTER");
    }

    @AfterClass
    public static void tearDownClas(){
        System.out.println("AFTER CLASS");

        //SE TIENE QUE CERRAR AQUI OBLIGATORIO
    }


}
