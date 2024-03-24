package priv.stud.database.entities.warehouse;

import lombok.*;


import priv.stud.database.entities.ropes.Rope;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Entity(name = "WAREHOUSE_ROPE")
public class WarehouseRope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @NotNull(message = "rope is null")
    @ManyToOne
    private Rope rope;

    @NotNull(message = "warehouse is null")
    @ManyToOne
    private Warehouse warehouse;

    protected void setId(Long id){
        this.id = id;
    }
    public WarehouseRope(int amount, @NonNull  Rope rope, @NonNull Warehouse warehouse) {
        this.amount = amount;
        this.rope = rope;
        this.warehouse = warehouse;
    }
}
