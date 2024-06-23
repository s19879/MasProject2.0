package priv.stud.database.services;

import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.entities.warehouse.Worker;

import java.util.List;

public interface WarehouseService {
    Warehouse addWarehouse(String name, Address address, List<Worker> workerList);
    Warehouse getWarehouseById(int id);
    Warehouse getWarehouseByName(String name);
    void addRopeToStock(int amount, Rope rope, Warehouse warehouse);
    void updateAmountOfRopeOnStock(int amount, WarehouseRope rope);
    boolean checkAvailabilityOfRope(Rope rope, Warehouse warehouse);
    List<Order> getAllOrdersInMonth(Warehouse warehouse);
    Order addOrder(Warehouse warehouse, Store store);
}
