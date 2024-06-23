package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.warehouse.*;

import java.sql.Date;

public class WorkerServiceImpl implements WorkerService{
    @Override
    public WorkerBuilder builder(@NonNull PersonalWorkerData personalWorkerData,  @NonNull Address address) {
        return new WorkerBuilderImpl(personalWorkerData, address);
    }


    private static class WorkerBuilderImpl implements WorkerBuilder {
        private PersonalWorkerData personalWorkerData;
        private Address address;
        private String education;
        private Integer seniority;
        private String specialization;
        private boolean isWarehouseWorker;

        WorkerBuilderImpl(PersonalWorkerData personalWorkerData, Address address) {
            this.personalWorkerData = personalWorkerData;
            this.address = address;
        }

        @Override
        public WorkerBuilder asAccountant(String education) {
            this.education = education;
            return this;
        }

        @Override
        public WorkerBuilder asWarehouseWorker() {
            this.isWarehouseWorker = true;
            return this;
        }

        @Override
        public WorkerBuilder withManager(int seniority) {
            this.seniority = seniority;
            return this;
        }

        @Override
        public WorkerBuilder withWarehouseman(String specialization) {
            this.specialization = specialization;
            return this;
        }

        @Override
        public Worker build() {
            if (education != null) {
                return new Accountant(personalWorkerData, address, education);
            } else if (isWarehouseWorker) {
                WarehouseWorker warehouseWorker = new WarehouseWorker(personalWorkerData, address);
                if (seniority != null) {
                    Manager manager = new Manager(seniority);
                    warehouseWorker.setManager(manager);
                    manager.setWarehouseWorker(warehouseWorker);
                }
                if (specialization != null) {
                    Warehouseman warehouseman = new Warehouseman(specialization);
                    warehouseWorker.setWarehouseman(warehouseman);
                    warehouseman.setWarehouseWorker(warehouseWorker);
                }
                return warehouseWorker;
            }
            throw new IllegalArgumentException("Invalid worker configuration");
        }
    }
}
