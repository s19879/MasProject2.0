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

    @Embedded
    private PersonalWorkerData personalWorkerData;

    @Embedded
    private Address address;

    @ManyToOne
    private Warehouse warehouse;

    public Worker(@NonNull PersonalWorkerData personalWorkerData, @NonNull Address address){
        this.personalWorkerData = personalWorkerData;
        this.address = address;
    }

    @Override
    public String toString(){
        return personalWorkerData.getName() + " " + personalWorkerData.getLastName();
    }
}
