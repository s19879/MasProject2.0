package priv.stud.database.entities.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Workshop;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Workshop workshop;

    @ManyToOne
    private Store store;

}
