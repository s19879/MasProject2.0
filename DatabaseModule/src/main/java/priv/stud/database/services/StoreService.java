package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;

public class StoreService implements IStoreService{
    @Override
    public void sendDaylyEaringsAmount(Store store) {

    }

    @Override
    public boolean addOrderQualif(Store store, Order order) {
        return false;
    }

    @Override
    public Order findOrderQualif(Store store, int id) {
        return null;
    }
}
