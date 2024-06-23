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
@Entity(name = "WAREHOUSE_WORKER")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class WarehouseWorker extends Worker {
    public WarehouseWorker(@NonNull PersonalWorkerData personalWorkerData, @NonNull Address address){
        super(personalWorkerData, address);
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Manager manager;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Warehouseman warehouseman;
}
