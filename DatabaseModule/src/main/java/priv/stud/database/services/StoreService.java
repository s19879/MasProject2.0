package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;

import java.util.List;

public interface StoreService {
    //Store addStore(@NonNull String name, @NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode);

    Store addCompanyStore( @NonNull String name, @NonNull String mail, @NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode, @NonNull String nameOfManager, int employeesNumber);
    Store addExternalStore(@NonNull String name, @NonNull String mail, @NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode, double loyality);

    boolean addExternalStoreToStore(Store store, double loyality);
    boolean addCompanyStoreToStore(Store store, @NonNull String nameOfManager, int employeesNumber);
    void sendDailyEaringsAmount(Store store);
    boolean addOrderQualif(Store store, Order order);
    Order findOrderQualif(Store store, int id);
    List<Store> findAllStores();

    Store findStoreById(Long id);

}
