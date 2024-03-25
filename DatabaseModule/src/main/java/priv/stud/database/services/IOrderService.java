package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;

public interface IOrderService {
    Order addOrder(Warehouse warehouse, Store store);
}
