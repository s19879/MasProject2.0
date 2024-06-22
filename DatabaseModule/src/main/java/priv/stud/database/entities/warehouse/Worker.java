package priv.stud.database.entities.warehouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import priv.stud.database.entities.Address;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "WORKER")
public abstract class Worker {
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

    public Worker(@NonNull String name, @NonNull String lastName, @NonNull String pesel, @NonNull Date birthDate){
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
        this.birthDate = birthDate;
    }
}
