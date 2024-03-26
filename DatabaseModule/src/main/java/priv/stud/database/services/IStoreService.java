package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;

public interface IStoreService {
    void sendDaylyEaringsAmount(Store store);
    boolean addOrderQualif(Store store, Order order);
    Order findOrderQualif(Store store, int id);

}
