package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.repositories.OrderRepository;
import priv.stud.database.repositories.StoreRepository;

import java.util.Map;
import java.util.TreeMap;

public class StoreService implements IStoreService{

    private final StoreRepository storeRepository;


    public StoreService() {
        storeRepository = new StoreRepository();

    }


    @Override
    public Store addStore(@NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode){
        Store store = new Store(city, street, houseNumber, zipCode);
        storeRepository.save(store);
        return store;
    }

    @Override
    public void sendDaylyEaringsAmount(Store store) {

    }

    @Override
    public boolean addOrderQualif(Store store, Order order) {
        Map<Long, Order> orderQualif = store.getOrdersQualif();
        if(orderQualif == null)
            orderQualif =new TreeMap<>();

        if(!orderQualif.containsKey(order.getId())){
            orderQualif.put(order.getId(), order);
            storeRepository.save(store);
        }


        return false;
    }

    @Override
    public Order findOrderQualif(Store store, int id) {
        return null;
    }
}
