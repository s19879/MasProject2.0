package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.repositories.WarehouseRopeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WarehouseRopeServiceImpl implements WarehouseRopeService {
    private final WarehouseRopeRepository repository;

    public WarehouseRopeServiceImpl(){
        repository = new WarehouseRopeRepository();
    }

    @Override
    public WarehouseRope getWarehouseRope(@NonNull Rope rope, @NonNull Warehouse warehouse) {
        return repository.getWarehouseRope(rope,warehouse);
    }

    @Override
    public void saveWarehouseRope(WarehouseRope warehouseRope) {
        repository.save(warehouseRope);
    }


    public void updateAmount(@NonNull WarehouseRope warehouseRope) {
        repository.save(warehouseRope);
    }

    public void saveWarehouseRope(int amount, @NonNull Rope rope, @NonNull  Warehouse warehouse){
        repository.save(new  WarehouseRope(amount, rope, warehouse));
    }
}
