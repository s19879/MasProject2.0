package priv.stud;

import priv.stud.database.entities.Address;
import priv.stud.database.entities.ropes.*;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.*;
import priv.stud.database.services.*;
import priv.stud.forms.MainForm;

import javax.swing.*;
import java.io.File;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File database = new File("./testdb");
        if(!database.exists()){
            //        //Create entities
            //Liny
            RopeService rs = ServiceFactory.getRopeService();
            BasicRopeInfo bri = new BasicRopeInfo(499.99, 60, 1, 1, "ZYGZAK", 1, false);
            Rope rope = rs.createNewCommonRope("Dragon", 1, 0.1, RopeType.DYNAMIC, bri, CommonRopeKind.SINGLE.name());
            Rope rope2 = rs.createNewTwinRope("IceMaster", 1, 0.1, RopeType.DYNAMIC, bri, false);
            Rope rope3 = rs.createNewCommonRope("Kamikaze", 1, 0.1, RopeType.DYNAMIC, bri, CommonRopeKind.SINGLE.name());

            //Pracownicy
            WorkerService workerService = ServiceFactory.getWorkerService();
            Worker worker = workerService
                    .builder(
                            new PersonalWorkerData("Jan", "Kowalski", "61092995393", Date.valueOf("1961-09-29")),
                            new Address("Wrocław", "Kamienna", "22", "51-212")
                    )
                    .asWarehouseWorker()
                    .withWarehouseman("Magazynier")
                    .build();

            Worker worker2 = workerService
                    .builder(
                            new PersonalWorkerData("Adam", "Zasada", "76040976511", Date.valueOf("1976-04-09")),
                            new Address("Wrocław", "Kamienna", "22", "51-212")
                    )
                    .asWarehouseWorker()
                    .withManager(5)
                    .build();

            //zamiana dziedziczenie dynamiczne
            if(workerService.getWorkerRole(worker2).equals("WarehouseWorker") &&
                    workerService.isWarehouseWorkerManager((WarehouseWorker) worker2)) {
                worker2 = workerService.changeWarehouseWorkerToWarehouseman((WarehouseWorker) worker2, "Wózki widłowe");
            }

            if(workerService.getWorkerRole(worker).equals("WarehouseWorker") &&
                    workerService.isWarehouseWorkerWarehouseman((WarehouseWorker) worker2)){
                worker = workerService.changeWarehouseWorkerToManager((WarehouseWorker) worker, 3);
            }


            //Dodawanie i uzupełnianie magazynu
            WarehouseService ws = ServiceFactory.getWarehouseService();
            Warehouse warehouse = ws.addWarehouse("Magazyn pierwszy",
                    new Address("Wrocław", "Przestrzenna", "12", "02-122"),
                    List.of(worker, worker2));

           ws.addRopeToStock(20, rope, warehouse);
            ws.addRopeToStock(14, rope2, warehouse);
            ws.addRopeToStock(10, rope3, warehouse);

            //Dodawanie sklepów
            StoreService storeService = ServiceFactory.getStoreService();
            Store store = storeService.addExternalStore("Sklep 1", "sklep1@somestore.com", "Wrocław", "Przestrzenna", "12", "02-122", 0.22);
            storeService.addExternalStoreToStore(store, 0.33);
            storeService.addCompanyStoreToStore(store, "Adam Kowalski", 14);

            ws.addOrder(warehouse, store);

            Store storeForSwing = storeService.addCompanyStore("Sklep 2", "store2@linex.pl", "Warszawa", "Żubra", "1", "01-066", "Jan kowalski", 5);

            Store store3 = storeService.addCompanyStore("Sklep 3", "store3@linex.pl", "Warszawa", "Obozowa", "76", "02-425", "Adam Mickiewicz", 10);

        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm();
            }
        });

    }
}