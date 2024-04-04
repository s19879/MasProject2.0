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


    protected void updateAmount(@NonNull WarehouseRope warehouseRope) {
        repository.save(warehouseRope);
    }

    protected void saveWarehouseRope(int amount, @NonNull Rope rope, @NonNull  Warehouse warehouse){
        List<WarehouseRope> warehouseRopes = warehouse.getWarehouseRopes();
        WarehouseRope wr = new  WarehouseRope(amount, rope, warehouse);

        if(warehouseRopes == null)
            warehouseRopes = new ArrayList<>();

        warehouseRopes.add(new WarehouseRope(amount, rope, warehouse));
        warehouse.setWarehouseRopes(warehouseRopes);
        repository.save(wr);
    }
}
