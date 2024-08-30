package priv.stud.database.services;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ServiceFactory jest klasą pomocniczą, która pozwala odwoływać się do serwisów, bez konieczności bezpośredniej
 * inicjazji implementacji (ServiceFactory.get[...]Service())
 */
@NoArgsConstructor
public class ServiceFactory {
    /**
     * Tworzy obiekt serwisu do obsługi zamówionego modelu w zamówieniu
     */
    @Getter
    private final static OrderedModelService orderedModelService = new OrderedModelServiceImpl();

    /**
     * Tworzy obiekt serwisu do obsługi zamówienia
     */
    @Getter
    private final static OrderService orderService = new OrderServiceImpl();

    /**
     * Tworzy obiekt serwisu do obsługi operacji związanych z linami
     */
    @Getter
    private final static RopeService ropeService = new RopeServiceImpl();

    /**
     * Tworzy obiekt serwisu do obsługi operacji sklepu
     */
    @Getter
    private final static StoreService storeService = new StoreServiceImpl();

    /**
     * Tworzy obiekt serwisu do obsługi operacji na linach w magazynie
     */
    @Getter
    private final static WarehouseRopeService warehouseRopeService = new WarehouseRopeServiceImpl();

    /**
     * Tworzy obiekt serwisu do obsługi operacji magazynu
     */
    @Getter
    private final  static WarehouseService warehouseService = new WarehouseServiceImpl();

    /**
     * Tworzy obiekt serwisu do obsługi operacji pracowników w systemie
     */
    @Getter
    private final static WorkerService workerService = new WorkerServiceImpl();
}
