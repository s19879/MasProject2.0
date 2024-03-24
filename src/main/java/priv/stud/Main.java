package priv.stud;

import priv.stud.database.entities.ropes.BasicRopeInfo;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.services.RopeService;
import priv.stud.database.services.WarehouseRopeService;
import priv.stud.database.services.WarehouseService;

import java.util.Set;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
       /* DummyService dummy = new DummyService();
        System.out.println("Hello world!");
        RopeService rs = new RopeService();
        BasicRopeInfo bri = new BasicRopeInfo(499.99, 60, 1, 1, "ZYGZAK", 1, false);
        //rs.createNewCommonRope("Dragon", 1, 0.1, bri);
        Workshop workshop = new Workshop();
        CommonRope commonRope = new CommonRope();
        commonRope.setBasicRopeInfo(bri);
        WorkshopLine line = new WorkshopLine(1, commonRope, workshop);

*/
        /*WarehouseService ws = new WarehouseService();
        RopeService rs = new RopeService();
        BasicRopeInfo bri = new BasicRopeInfo(499.99, 60, 1, 1, "ZYGZAK", 1, false);
        Rope rope = rs.createNewCommonRope("Dragon", 1, 0.1, bri);
        Warehouse warehouse = ws.addWarehouse("Pierwszy", "Wrocław", "Przestrzenna", "12", "02-122");
        ws.addRopeToStock(20, rope, warehouse);
        for(WarehouseRope r : warehouse.getWarehouseRopes()){
            System.out.println(r.getAmount());
        }*/
        WarehouseService service = new WarehouseService();
        Warehouse w = service.getWarehouseById(1);
        Set<WarehouseRope> r = w.getWarehouseRopes();




    }
}