package priv.stud.database.services;

import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.entities.warehouse.Worker;

import java.util.List;
import java.util.Set;


/**
 * Serwis do akcji na magazynie
 */
public interface WarehouseService {

    /**
     * Dodanie magazynu
     *
     * @param name - nazwa magazynu
     * @param address - adres
     * @param workerList - lista pracowników
     * @return zwracany obiekt magazynu
     */
    Warehouse addWarehouse(String name, Address address, Set<Worker> workerList);

    /**
     * Pobranie magazynu po id
     *
     * @param id
     * @return
     */
    Warehouse getWarehouseById(long id);

    void updateWarehouse(Warehouse warehouse);

    Warehouse refreshWarehouse(Warehouse warehouse);
    Warehouse getWarehouseByName(String name);

    /**
     * dodawanie liny do magazynu
     * @param amount - ilość
     * @param rope - lina
     * @param warehouse - magazyn
     */
    void addRopeToStock(int amount, Rope rope, Warehouse warehouse);
    /**
     * Dodawanie zamówienia
     *
     * @param warehouse - magazyn
     * @param store -sklep
     * @return
     */
    Order addOrder(Warehouse warehouse, Store store);

    /**
     * Znalezienie wszystkich magazynów
     *
     * @return lista magazynów
     */
    public List<Warehouse> findAllWarehouses();


    void updateAmountOfRopeOnStock(int amount, WarehouseRope rope);
    boolean checkAvailabilityOfRope(Rope rope, Warehouse warehouse);
    List<Order> getAllOrdersInMonth(Warehouse warehouse);
}
