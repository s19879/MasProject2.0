package priv.stud.database.repositories;

import priv.stud.database.entities.warehouse.WarehouseRope;

public class WarehouseRopeRepository extends AbstractRepository<WarehouseRope, Long> {
    public WarehouseRopeRepository() {
        super(WarehouseRope.class);
    }
}
