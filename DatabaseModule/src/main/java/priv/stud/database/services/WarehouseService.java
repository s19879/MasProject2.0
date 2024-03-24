package priv.stud.database.services;

import priv.stud.database.entities.Address;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseLine;
import priv.stud.database.repositories.WarehouseRepository;

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
    public void addRope(int amount, Rope rope) {

    }

    @Override
    public boolean checkAvailabilityOfRope(Rope rope) {
        return false;
    }

    @Override
    public void updateRopeAmount(WarehouseLine warehouseLine) {

    }
}
