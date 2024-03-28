package priv.stud.database.entities.warehouse;

import lombok.*;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<WarehouseRope> warehouseRopes = new HashSet<>();

    @OneToMany(mappedBy = "warehouse")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "warehouse")
    private Set<Worker> workers = new HashSet<>();


}
