package priv.stud.database.entities.stores;

import lombok.*;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity(name = "STORE")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "store")
    @MapKey(name = "id")
    private Map<Long, Order> ordersQualif = new HashMap<>();


    public Store(@NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode) {
        this.address = new Address(city, street, houseNumber, zipCode);
    }
}


