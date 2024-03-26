package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.repositories.OrderRepository;

import java.util.List;

public interface IOrderService {
    double calculateTotalPrice();
    void changeStatus(Enum<OrderStatus> orderStatus, Order order);
    boolean updateOrder(Order order);
    boolean deleteOrder(Order order);
    static List<Order> getOrderList(){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.findAll();
    }
}
