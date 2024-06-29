package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.ropes.Rope;

/**
 * Serwis zawierający akcje wykonywanych na klasie OrderedModel
 */
public interface OrderedModelService {
    /**
     * Dodaje zamówiony model
     *
     * @param rope - lina zawarta w zamówieniu
     * @param order -  zamówienie
     * @param amount - ilość lin dodanych do zamówienia
     * @param isReducesValue - czy ilość pomniejszona o maksymalną liczbę lin w magazynie
     * @return zwracany obiekt zamówionego modelu
     */
    OrderedModel addOrderedModel(Rope rope, Order order, int amount, boolean isReducesValue);
    double  calculatePrice(OrderedModel orderedModel);
}
