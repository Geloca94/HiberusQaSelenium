package EjercicioSeleniumJunit.Support;


import EjercicioSeleniumJunit.Model.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class TestDataContext {

    private static final List<InventoryItem> inventoryItemListInCart = new ArrayList<>();

    public static void addItem(InventoryItem item){ inventoryItemListInCart.add(item);}

    public static List<InventoryItem> getInventoryItemListInCart(){
        return inventoryItemListInCart;
    }

}
