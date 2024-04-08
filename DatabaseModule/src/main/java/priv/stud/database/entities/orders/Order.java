package priv.stud.database.entities.orders;

import lombok.*;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    @Basic
    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy = "order")
    private List<OrderedModel> orderedModels;

    public Order(@NonNull Store store, @NonNull Warehouse warehouse){
        date = new Date();
        this.store = store;
        this.warehouse = warehouse;
        this.status = OrderStatus.OPEN;
        orderedModels = new ArrayList<>();
    }

}
