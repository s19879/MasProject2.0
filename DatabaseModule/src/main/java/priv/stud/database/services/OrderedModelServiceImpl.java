package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.repositories.OrderRepository;
import priv.stud.database.repositories.OrderedModelRepository;

public class OrderedModelServiceImpl implements OrderedModelService{

    private final OrderRepository orderRepository;

    public OrderedModelServiceImpl(){
            orderRepository = new OrderRepository();
    }

    @Override
    public OrderedModel addOrderedModel(Rope rope, Order order, int amount, boolean isReducedValue) {
        OrderedModel orderedModel = new OrderedModel(rope, order, amount, isReducedValue);
        order.getOrderedModels().add(orderedModel);
        orderRepository.save(order);
        return orderedModel;
    }

    @Override
    public double calculatePrice(OrderedModel orderedModel) {
        return orderedModel.getAmount() * orderedModel.getRope().getBasicRopeInfo().getPrice();
    }
}
