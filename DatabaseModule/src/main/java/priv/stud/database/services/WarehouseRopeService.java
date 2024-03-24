package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.repositories.WarehouseRopeRepository;

import java.util.HashSet;
import java.util.Set;

public class WarehouseRopeService implements IWarehouseRopeService {
    private final WarehouseRopeRepository repository;

    protected WarehouseRopeService(){
        repository = new WarehouseRopeRepository();
    }

    @Override
    public WarehouseRope getWarehouseRope(@NonNull Rope rope, @NonNull Warehouse warehouse) {
        return null;
    }


    protected void updateAmount(WarehouseRope warehouseRope) {
        repository.save(warehouseRope);
    }

    protected void saveWarehouseRope(int amount, Rope rope, Warehouse warehouse){
        Set<WarehouseRope> warehouseRopes = warehouse.getWarehouseRopes();
        WarehouseRope wr = new  WarehouseRope(amount, rope, warehouse);

        if(warehouseRopes == null)
            warehouseRopes = new HashSet<>();

        warehouseRopes.add(new WarehouseRope(amount, rope, warehouse));
        warehouse.setWarehouseRopes(warehouseRopes);
        repository.save(wr);
    }
}
