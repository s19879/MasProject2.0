package priv.stud.database.entities.warehouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "WAREHOUSEMAN")
public class Warehouseman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String specialization;

    @OneToOne(mappedBy = "warehouseman", cascade = CascadeType.ALL)
    private WarehouseWorker warehouseWorker;

    public Warehouseman(@NonNull String specialization){
        this.specialization = specialization;
    }

}
