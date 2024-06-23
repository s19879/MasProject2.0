package priv.stud.database.repositories;

import priv.stud.database.entities.warehouse.Worker;

public class WorkerRepository extends AbstractRepository<Worker, Long> {
    public  WorkerRepository() {
        super(Worker.class);
    }
}
