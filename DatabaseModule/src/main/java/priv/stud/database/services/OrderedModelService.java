package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.ropes.Rope;

public interface OrderedModelService {
    OrderedModel addOrderedModel(Rope rope, Order order, int amount, boolean isReducesValue);
    double  calculatePrice(OrderedModel orderedModel);
}
