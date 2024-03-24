package priv.stud.database.repositories;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

import java.util.List;

public class WarehouseRepository extends AbstractRepository<Warehouse, Long> {
    public WarehouseRepository() {
        super(Warehouse.class);
    }

}
