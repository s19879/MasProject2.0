package priv.stud.database.repositories;

import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

public interface IWarehouseRopeRepository {
    WarehouseRope getWarehouseRope(Rope rope, Warehouse warehouse);
}
