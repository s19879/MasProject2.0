package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.repositories.OrderRepository;

import java.util.List;

/**
 * Serwis z akcjami dla zamówienia
 *
 * @@author Przemysław Mizgała
 */
public interface OrderService {

    /**
     * Dodaje zamówienie do bazy
     *
     * @param warehouse - magazyn w którym odbywa się zamówienie
     * @param store - sklep, dla którego jest robione zamówienie
     * @return zwracany obiekt zamówienia
     */
    Order addOrder(Warehouse warehouse, Store store);

    /**
     * Aktualizacja zamówienia
     *
     * @param order - zamówienie
     * @return boolean zwracający informacje czy zapis się udał
     */
    boolean updateOrder(Order order);

    /**
     * Zmiana statusu zamówienia
     *
     * @param order - zamówienie, którego zmiana dotyczy
     * @param status - status, na który chcemy zmienić
     * @return boolean zwracający informację czy zmiana się udała
     */
    boolean changeStatus(Order order, OrderStatus status);
    boolean deleteOrder(Order order);
    double calculateTotalPrice();
    void changeStatus(OrderStatus orderStatus, Order order);

    Order getOrderById(Long id);

    static List<Order> getOrderList(){
        OrderRepository orderRepository = new OrderRepository();
        return orderRepository.findAll();
    }
}
