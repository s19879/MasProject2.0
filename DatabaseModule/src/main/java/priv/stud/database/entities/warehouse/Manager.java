package priv.stud.database.entities.warehouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@NoArgsConstructor
@Setter
@Getter
@Entity(name = "MANAGER")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    int seniority;

    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
    private WarehouseWorker warehouseWorker;

    public Manager(int seniority) {
        this.seniority = seniority;
    }

}
