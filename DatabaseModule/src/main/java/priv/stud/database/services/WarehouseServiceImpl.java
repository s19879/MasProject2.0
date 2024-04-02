package priv.stud.database.services;

import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.repositories.WarehouseRepository;

import java.util.List;

public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository repository;
    private final static OrderServiceImpl orderService = new OrderServiceImpl();
    private final static StoreServiceImpl storeService = new StoreServiceImpl();

    public WarehouseServiceImpl(){
        repository = new WarehouseRepository();
    }

    @Override
    public Warehouse addWarehouse(String name, String city, String street, String streetNumber, String zipCode){
        Address address = new Address(city, street, streetNumber, zipCode);
        return repository.save(new Warehouse(name,address));
    }

    @Override
    public Warehouse getWarehouseById(int id) {
        return repository.findById((long) id);
    }

    @Override
    public Warehouse getWarehouseByName(String name) {
        return repository.findByFieldName("name", name);
    }

    @Override
    public void addRopeToStock(int amount, Rope rope, Warehouse warehouse) {
        WarehouseRopeServiceImpl wrs = new WarehouseRopeServiceImpl();
        wrs.saveWarehouseRope(amount, rope, warehouse);
    }

    @Override
    public void updateAmountOfRopeOnStock(int amount, WarehouseRope rope) {
        WarehouseRopeServiceImpl wrs = new WarehouseRopeServiceImpl();
        rope.setAmount(amount);
        wrs.updateAmount(rope);
    }

    @Override
    public boolean checkAvailabilityOfRope(Rope rope, Warehouse warehouse) {
        return false;
    }

    @Override
    public List<Order> getAllOrdersInMonth(Warehouse warehouse) {
        return null;
    }

    @Override
    public Order addOrder(Warehouse warehouse, Store store) {
        Order order = orderService.addOrder(warehouse, store);
        warehouse.getOrders().add(order);
        repository.save(warehouse);
        storeService.addOrderQualif(store, order);
        return order;
    }

}
