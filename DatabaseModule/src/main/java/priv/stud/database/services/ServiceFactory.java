package priv.stud.database.services;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceFactory {
    @Getter
    private final static OrderedModelService orderedModelService = new OrderedModelServiceImpl();

    @Getter
    private final static OrderService orderService = new OrderServiceImpl();

    @Getter
    private final static RopeService ropeService = new RopeServiceImpl();

    @Getter
    private final static StoreService storeService = new StoreServiceImpl();

    @Getter
    private final static WarehouseRopeService warehouseRopeService = new WarehouseRopeServiceImpl();

    @Getter
    private final  static WarehouseService warehouseService = new WarehouseServiceImpl();

    @Getter
    private final static WorkerService workerService = new WorkerServiceImpl();
}
