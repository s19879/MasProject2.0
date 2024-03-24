package priv.stud.database.services;

import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

public interface IWarehouseRopeService {
    WarehouseRope getWarehouseRope(Rope rope, Warehouse warehouse);
}
