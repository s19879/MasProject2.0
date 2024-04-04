package priv.stud;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.BasicRopeInfo;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.ropes.RopeType;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.services.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//
//        //Create entities
//        RopeServiceImpl rs = new RopeServiceImpl();
//        BasicRopeInfo bri = new BasicRopeInfo(499.99, 60, 1, 1, "ZYGZAK", 1, false);
//        Rope rope = rs.createNewCommonRope("Dragon", 1, 0.1, RopeType.DYNAMIC, bri);
//
//        WarehouseServiceImpl ws = new WarehouseServiceImpl();
//        Warehouse warehouse = ws.addWarehouse("Pierwszy", "Wrocław", "Przestrzenna", "12", "02-122");
//
//
//        ws.addRopeToStock(20, rope, warehouse);
//
//        //Read from entties
//        Warehouse warehouse1 = ws.getWarehouseByName("Pierwszy");
//        System.out.println(warehouse1.getAddress());
//
//        WarehouseRopeServiceImpl wrs = new WarehouseRopeServiceImpl();
//        WarehouseRope wr = wrs.getWarehouseRope(rope, warehouse);
//        ws.updateAmountOfRopeOnStock(12, wr);
////        ws.updateAmountOfRopeOnStock(2, );
//
//
//        StoreServiceImpl storeService = new StoreServiceImpl();
//        Store store = storeService.addStore("Wrocław", "Przestrzenna", "12", "02-122");
//
//        ws.addOrder(warehouse1, store);
//
//        for(Long key : store.getOrdersQualif().keySet()){
//            System.out.println(key + ":" + store.getOrdersQualif().get(key));
//        }
//
//        for(Store st : storeService.getAllStores()){
//            for(Long key : st.getOrdersQualif().keySet()){
//                System.out.println(key + ":" + st.getOrdersQualif().get(key));
//            }
//        }
//
//
//        Store storeForSwing = storeService.addStore("Warszawa", "Żubra", "1", "01-066");
        RopeServiceImpl rs = new RopeServiceImpl();

        List<Rope> ropeList = rs.getAllRopes();

        for(Rope rop : ropeList){
            System.out.println("Dla rope o id " + rop.getId() + " o nazwie " + rop.getName() + " i typie " + rop.getRopeType());
            for(WarehouseRope wRope : rop.getWarehouseRopes()){
                System.out.println("Dla magazynu o nazwie "
                        + wRope.getWarehouse().getName() + " i id "
                        + wRope.getWarehouse().getId() +
                        " ilość lin wynosi: " + wRope.getAmount());
            }
        }

        Store store = new StoreServiceImpl().addStore("Warszawa", "Obozowa", "76", "02-425");

        OrderedModelService oMS = new OrderedModelServiceImpl();
        //oMS.addOrderedModel()
    }
}