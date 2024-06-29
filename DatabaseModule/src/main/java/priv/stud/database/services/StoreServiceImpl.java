package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.CompanyStore;
import priv.stud.database.entities.stores.ExternalStore;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.repositories.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;


    public StoreServiceImpl() {
        storeRepository = new StoreRepository();

    }
    @Override
    public Store addCompanyStore(@NonNull String name, @NonNull String mail, @NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode, @NonNull String nameOfManager, int employeesNumber) {
        Store store = new Store(name, mail, city, street, houseNumber, zipCode);
        store.setCompanyStore(new CompanyStore(nameOfManager, employeesNumber));
        storeRepository.save(store);
        return store;
    }

    @Override
    public Store addExternalStore(@NonNull String name, @NonNull String mail, @NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode, double loyality) {
        Store store = new Store(name, mail, city, street, houseNumber, zipCode);
        store.setExternalStore(new ExternalStore(loyality));
        storeRepository.save(store);
        return store;
    }

    @Override
    public boolean addExternalStoreToStore(Store store, double loyality) {
        if(store.getExternalStore() == null){
            store.setExternalStore(new ExternalStore(loyality));
            storeRepository.save(store);
            return true;
        } else return false;
    }

    @Override
    public boolean addCompanyStoreToStore(Store store, @NonNull String nameOfManager, int employeesNumber) {
        if(store.getCompanyStore() == null){
            store.setCompanyStore(new CompanyStore(nameOfManager, employeesNumber));
            storeRepository.save(store);
            return true;
        } else return false;
    }

    @Override
    public void sendDailyEaringsAmount(Store store) {

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

    @Override
    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store findStoreById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<String> getStoreTypes(Store store) {
        List<String> roleTypes = new ArrayList<>();
        if(store.getCompanyStore() != null) roleTypes.add("CompanyStore");
        if(store.getExternalStore() != null) roleTypes.add("ExternalStore");
        return roleTypes;
    }

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }
}
