package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.repositories.OrderRepository;

import java.util.List;

public interface IOrderService {
    Order addOrder(Warehouse warehouse, Store store);
    boolean updateOrder(Order order);
    boolean deleteOrder(Order order);
    double calculateTotalPrice();
    void changeStatus(Enum<OrderStatus> orderStatus, Order order);

    static List<Order> getOrderList(){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.findAll();
    }
}
