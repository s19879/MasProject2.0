package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.repositories.OrderRepository;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(){
        repository = new OrderRepository();
    }

    @Override
    public Order addOrder(Warehouse warehouse, Store store) {
        Order order = new Order(store, warehouse);
        repository.save(order);
        return order;
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public boolean changeStatus(Order order, OrderStatus status) {
        return false;
    }

    @Override
    public boolean deleteOrder(Order order) {
        return false;
    }




    @Override
    public double calculateTotalPrice() {
        return 0;
    }

    @Override
    public void changeStatus(OrderStatus orderStatus, Order order) {
            order.setStatus(orderStatus);
    }


}
