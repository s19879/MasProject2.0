package priv.stud.database.services;

import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

public interface WarehouseRopeService {
    WarehouseRope getWarehouseRope(Rope rope, Warehouse warehouse);

    void saveWarehouseRope(WarehouseRope warehouseRope);

    void saveWarehouseRope(int amount, Rope rope, Warehouse warehouse);

    void updateAmount(WarehouseRope rope);
}
