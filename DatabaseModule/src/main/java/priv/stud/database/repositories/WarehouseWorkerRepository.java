package priv.stud.database.repositories;

import priv.stud.database.entities.warehouse.WarehouseWorker;

public class WarehouseWorkerRepository extends AbstractRepository<WarehouseWorker, Long> {
    public WarehouseWorkerRepository() {
        super(WarehouseWorker.class);
    }
}
