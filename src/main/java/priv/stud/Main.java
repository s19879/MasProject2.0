package priv.stud;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.*;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.services.*;
import priv.stud.forms.MainForm;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File database = new File("./testdb");
        if(!database.exists()){
            //        //Create entities
            RopeServiceImpl rs = new RopeServiceImpl();
            BasicRopeInfo bri = new BasicRopeInfo(499.99, 60, 1, 1, "ZYGZAK", 1, false);
            Rope rope = rs.createNewCommonRope("Dragon", 1, 0.1, RopeType.DYNAMIC, bri, CommonRopeKind.SINGLE.name());
            Rope rope2 = rs.createNewTwinRope("IceMaster", 1, 0.1, RopeType.DYNAMIC, bri, false);
            Rope rope3 = rs.createNewCommonRope("Kamikaze", 1, 0.1, RopeType.DYNAMIC, bri, CommonRopeKind.SINGLE.name());
            WarehouseServiceImpl ws = new WarehouseServiceImpl();
            Warehouse warehouse = ws.addWarehouse("Pierwszy", "Wrocław", "Przestrzenna", "12", "02-122");


            ws.addRopeToStock(20, rope, warehouse);
            ws.addRopeToStock(14, rope2, warehouse);
            ws.addRopeToStock(10, rope3, warehouse);


            Warehouse warehouse1 = ws.getWarehouseByName("Pierwszy");
            System.out.println(warehouse1.getAddress());

            WarehouseRopeServiceImpl wrs = new WarehouseRopeServiceImpl();
            WarehouseRope wr = wrs.getWarehouseRope(rope, warehouse);
            ws.updateAmountOfRopeOnStock(12, wr);
    //        ws.updateAmountOfRopeOnStock(2, );


            StoreServiceImpl storeService = new StoreServiceImpl();
            Store store = storeService.addExternalStore("Sklep 1", "Wrocław", "Przestrzenna", "12", "02-122", 0.22);
            System.out.println(storeService.addExternalStoreToStore(store, 0.33));
            System.out.println(storeService.addCompanyStoreToStore(store, "Adam Kowalski", 14));

            ws.addOrder(warehouse1, store);

            for(Long key : store.getOrdersQualif().keySet()){
                System.out.println(key + ":" + store.getOrdersQualif().get(key));
            }

//            for(Store st : storeService.getAllStores()){
//                for(Long key : st.getOrdersQualif().keySet()){
//                    System.out.println(key + ":" + st.getOrdersQualif().get(key));
//                }
//            }


            Store storeForSwing = storeService.addCompanyStore("Sklep 2", "Warszawa", "Żubra", "1", "01-066", "Jan kowalski", 5);

            List<Rope> ropeList = rs.getAllRopes();

            for(Rope rop : ropeList){
                System.out.println("Dla rope o id " + rop.getId() + " o nazwie " + rop.getName() + " i typie " + rop.getRopeType());
//                for(WarehouseRope wRope : rop.getWarehouseRopes()){
//                    System.out.println("Dla magazynu o nazwie "
//                            + wRope.getWarehouse().getName() + " i id "
//                            + wRope.getWarehouse().getId() +
//                            " ilość lin wynosi: " + wRope.getAmount());
//                }
            }

            Store store3 = storeService.addCompanyStore("Sklep 3", "Warszawa", "Obozowa", "76", "02-425", "Adam Mickiewicz", 10);

            OrderedModelService oMS = new OrderedModelServiceImpl();

        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm();
            }
        });

        //oMS.addOrderedModel()
    }
}