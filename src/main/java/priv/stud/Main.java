package priv.stud;

import priv.stud.database.entities.ropes.BasicRopeInfo;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.services.*;

public class Main {
    public static void main(String[] args) {

        //Create entities
        RopeServiceImpl rs = new RopeServiceImpl();
        BasicRopeInfo bri = new BasicRopeInfo(499.99, 60, 1, 1, "ZYGZAK", 1, false);
        Rope rope = rs.createNewCommonRope("Dragon", 1, 0.1, bri);

        WarehouseServiceImpl ws = new WarehouseServiceImpl();
        Warehouse warehouse = ws.addWarehouse("Pierwszy", "Wrocław", "Przestrzenna", "12", "02-122");


        ws.addRopeToStock(20, rope, warehouse);

        //Read from entties
        Warehouse warehouse1 = ws.getWarehouseByName("Pierwszy");
        System.out.println(warehouse1.getAddress());

        WarehouseRopeServiceImpl wrs = new WarehouseRopeServiceImpl();
        WarehouseRope wr = wrs.getWarehouseRope(rope, warehouse);
        ws.updateAmountOfRopeOnStock(12, wr);
//        ws.updateAmountOfRopeOnStock(2, );


        StoreServiceImpl storeService = new StoreServiceImpl();
        Store store = storeService.addStore("Wrocław", "Przestrzenna", "12", "02-122");

        ws.addOrder(warehouse1, store);

        for(Long key : store.getOrdersQualif().keySet()){
            System.out.println(key + ":" + store.getOrdersQualif().get(key));
        }

        for(Store st : storeService.getAllStores()){
            for(Long key : st.getOrdersQualif().keySet()){
                System.out.println(key + ":" + st.getOrdersQualif().get(key));
            }
        }


    }
}