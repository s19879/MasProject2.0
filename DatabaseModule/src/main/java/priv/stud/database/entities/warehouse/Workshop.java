package priv.stud.database.entities.warehouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity(name = "WORKSHOP")
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "workshop")
    private Set<WorkshopLine> workshopLines;

    @OneToMany(mappedBy = "workshop")
    private Set<Order> orders;

    @OneToMany(mappedBy = "workshop")
    private Set<Worker> workers;
}
