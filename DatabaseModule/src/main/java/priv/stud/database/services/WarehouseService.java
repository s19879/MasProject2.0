package priv.stud.database.services;

import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.repositories.WarehouseRepository;
import priv.stud.database.repositories.WarehouseRopeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WarehouseService implements IWarehouseService{
    private final WarehouseRepository repository;

    public WarehouseService(){
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
        WarehouseRopeService wrs = new WarehouseRopeService();
        wrs.saveWarehouseRope(amount, rope, warehouse);
    }

    @Override
    public void updateAmountOfRopeOnStock(int amount, WarehouseRope rope) {
        WarehouseRopeService wrs = new WarehouseRopeService();
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

}
