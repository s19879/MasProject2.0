package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.repositories.OrderRepository;

public class OrderService implements IOrderService{
    private final OrderRepository repository;

    public OrderService(){
        repository = new OrderRepository();
    }

    @Override
    public double calculateTotalPrice() {
        return 0;
    }

    @Override
    public void changeStatus(Enum<OrderStatus> orderStatus, Order order) {
            order.setStatus(orderStatus);
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public boolean deleteOrder(Order order) {
        return false;
    }
}
