package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;

public interface IStoreService {
    Store addStore(@NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode);
    void sendDaylyEaringsAmount(Store store);
    boolean addOrderQualif(Store store, Order order);
    Order findOrderQualif(Store store, int id);

}
