package priv.stud.database.services;

import priv.stud.database.entities.Address;
import priv.stud.database.entities.warehouse.PersonalWorkerData;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.Worker;

import java.sql.Date;

public interface WorkerService {
//    Worker createWarehouseman();
//    Worker createManager();
//    Worker createAccountant();
WorkerBuilder builder(PersonalWorkerData personalWorkerData, Address address);

    interface WorkerBuilder {
        WorkerBuilder asAccountant(String education);
        WorkerBuilder asWarehouseWorker();
        WorkerBuilder withManager(int seniority);
        WorkerBuilder withWarehouseman(String specialization);
        Worker build();
    }
}
