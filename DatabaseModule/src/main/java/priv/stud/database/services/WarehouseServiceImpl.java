package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.entities.warehouse.Worker;
import priv.stud.database.repositories.WarehouseRepository;
import priv.stud.database.repositories.WorkerRepository;

import java.util.List;

public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository repository;
    private final WorkerRepository workerRepository;
    private final static OrderService orderService = ServiceFactory.getOrderService();

    public WarehouseServiceImpl(){

        repository = new WarehouseRepository();
        workerRepository = new WorkerRepository();
    }

    @Override
    public Warehouse addWarehouse(@NonNull String name, @NonNull Address warehouseAddress, @NonNull List<Worker> workerList){
        if(!workerList.isEmpty()){
            Warehouse warehouse = new Warehouse(name,warehouseAddress);
            warehouse.setWorkers(workerList);
            repository.save(warehouse);
            workerList.stream()
                    .forEach(worker -> {
                        worker.setWarehouse(warehouse);
                        workerRepository.save(worker);
                    });
            return warehouse;
        }
        return null;
    }

    @Override
    public Warehouse getWarehouseById(int id) {
        return repository.findById((long) id);
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        repository.save(warehouse);
    }

    @Override
    public Warehouse getWarehouseByName(String name) {
        return repository.findByFieldName("name", name);
    }

    @Override
    public void addRopeToStock(int amount, Rope rope, Warehouse warehouse) {
        WarehouseRopeService wrs = ServiceFactory.getWarehouseRopeService();
        wrs.saveWarehouseRope(amount, rope, warehouse);
    }

    @Override
    public void updateAmountOfRopeOnStock(int amount, WarehouseRope rope) {
        WarehouseRopeService wrs = ServiceFactory.getWarehouseRopeService();
        rope.setAmount(amount);
        wrs.updateAmount(rope);
    }

    @Override
    public boolean checkAvailabilityOfRope(Rope rope, Warehouse warehouse) {
        return false;
    }

    @Override
    public List<Order> getAllOrdersInMonth(Warehouse warehouse) {
        return null;
    }

    @Override
    public Order addOrder(Warehouse warehouse, Store store) {
        Order order = orderService.addOrder(warehouse, store);
        //List<Order> orders = warehouse.getOrders();
        repository.save(warehouse);
        //storeService.addOrderQualif(store, order);
        return order;
    }

    @Override
    public List<Warehouse> findAllWarehouses() {
        return repository.findAll();
    }
}
