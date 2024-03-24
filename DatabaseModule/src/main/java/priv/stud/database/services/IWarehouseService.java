package priv.stud.database.services;

import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

public interface IWarehouseService {
    Warehouse addWarehouse(String name, String city, String street, String streetNumber, String zipCode);
    Warehouse getWarehouseById(int id);
    Warehouse getWarehouseByName(String name);
    void addRopeToStock(int amount, Rope rope, Warehouse warehouse);

    void updateAmountOfRopeOnStock(int amount, WarehouseRope rope);
    boolean checkAvailabilityOfRope(Rope rope, Warehouse warehouse);
    void updateRopeAmount(WarehouseRope warehouseRope);

}
