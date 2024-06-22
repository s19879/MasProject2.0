package priv.stud.database.entities.warehouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "WAREHOUSE_WORKER")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class WarehouseWorker extends Worker {
    public WarehouseWorker(@NonNull String name, @NonNull String lastName,
                           @NonNull String pesel, @NonNull Date birthDate){
        super(name, lastName, pesel, birthDate);
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Manager manager;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Warehouseman warehouseman;
}
