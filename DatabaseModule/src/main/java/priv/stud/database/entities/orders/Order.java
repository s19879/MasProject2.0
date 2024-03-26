package priv.stud.database.entities.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.services.IOrderService;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Type(type = "priv.stud.database.entities.orders.OrderStatus")
    Enum<OrderStatus> status;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy = "order")
    private Set<OrderedModel> orderedModels;

}
