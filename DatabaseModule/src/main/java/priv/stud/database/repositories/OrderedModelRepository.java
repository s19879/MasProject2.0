package priv.stud.database.repositories;

import priv.stud.database.entities.orders.OrderedModel;

public class OrderedModelRepository extends AbstractRepository<OrderedModel, Long> {
    public  OrderedModelRepository() {
        super(OrderedModel.class);
    }
}
