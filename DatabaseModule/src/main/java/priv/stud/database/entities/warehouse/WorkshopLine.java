package priv.stud.database.entities.warehouse;

import lombok.Data;
import lombok.NoArgsConstructor;
import priv.stud.database.entities.ropes.Rope;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity(name = "WORKSHOP_LINE")
public class WorkshopLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Rope rope;

    @ManyToOne
    private Workshop workshop;

}
