package priv.stud.database.services;

import priv.stud.database.repositories.OrderRepository;

public class OrderService {
    private final OrderRepository repository;

    public OrderService(){
        repository = new OrderRepository();
    }
}
