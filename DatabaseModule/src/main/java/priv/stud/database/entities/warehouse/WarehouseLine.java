package priv.stud.database.entities.warehouse;

import lombok.*;


import priv.stud.database.entities.ropes.Rope;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Entity(name = "WORKSHOP_LINE")
public class WarehouseLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @NotNull(message = "rope is null")
    @ManyToOne
    private Rope rope;

    @NotNull(message = "Kupa")
    @ManyToOne
    private Warehouse warehouse;

    protected void setId(Long id){
        this.id = id;
    }
    public WarehouseLine(int amount, @NonNull  Rope rope, @NonNull Warehouse warehouse) {
        this.amount = amount;
        this.rope = rope;
        this.warehouse = warehouse;
    }
}
