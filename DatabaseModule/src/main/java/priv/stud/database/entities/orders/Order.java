package priv.stud.database.entities.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;

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

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy = "order")
    private Set<OrderedModel> orderedModels;

}
