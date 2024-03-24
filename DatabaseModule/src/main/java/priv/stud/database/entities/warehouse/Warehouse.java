package priv.stud.database.entities.warehouse;

import lombok.*;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "WAREHOUSE")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "warehouse")
    private Set<WarehouseRope> warehouseRopes;

    @OneToMany(mappedBy = "warehouse")
    private Set<Order> orders;

    @OneToMany(mappedBy = "warehouse")
    private Set<Worker> workers;


}
