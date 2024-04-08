package priv.stud.database.services;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.repositories.OrderedModelRepository;

public class OrderedModelServiceImpl implements OrderedModelService{

    private final OrderedModelRepository repository;

    public OrderedModelServiceImpl(){
            repository = new OrderedModelRepository();
    }

    @Override
    public OrderedModel addOrderedModel(Rope rope, Order order, int amount, boolean isReducedValue) {
        return new OrderedModel(rope,order,amount,isReducedValue);
    }

    @Override
    public double calculatePrice(OrderedModel orderedModel) {
        return orderedModel.getAmount() * orderedModel.getRope().getBasicRopeInfo().getPrice();
    }
}
