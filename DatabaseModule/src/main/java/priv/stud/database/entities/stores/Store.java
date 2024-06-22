package priv.stud.database.entities.stores;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import priv.stud.database.entities.Address;
import priv.stud.database.entities.orders.Order;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor
@Setter
@Getter
@Entity(name = "STORE")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String mail;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapKey(name = "id")
    private Map<Long, Order> ordersQualif = new HashMap<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExternalStore externalStore = null;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CompanyStore companyStore = null;


    public Store(@NonNull String name, @NonNull String mail,@NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode) {
        this.address = new Address(city, street, houseNumber, zipCode);
        this.name = name;
        this.mail = mail;
    }

    @Override
    public String toString(){
        return name + ", " + address;
    }
}


