package priv.stud.database.entities.warehouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import priv.stud.database.entities.Address;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "WORKER")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String lastName;
    private String pesel;
    private Date birthDate;

    @Embedded
    private Address address;

    @ManyToOne
    private Warehouse warehouse;
}
