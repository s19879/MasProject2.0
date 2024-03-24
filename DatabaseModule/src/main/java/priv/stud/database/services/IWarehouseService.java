package priv.stud.database.services;

import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseLine;

public interface IWarehouseService {
    Warehouse addWarehouse(String name, String city, String street, String streetNumber, String zipCode);
    void addRope(int amount, Rope rope);
    boolean checkAvailabilityOfRope(Rope rope);
    void updateRopeAmount(WarehouseLine warehouseLine);
}
