package priv.stud.database.repositories;

import priv.stud.database.entities.orders.Order;

public class OrderRepository extends AbstractRepository<Order, Long>{

    public OrderRepository(){
        super(Order.class);
    }
}
