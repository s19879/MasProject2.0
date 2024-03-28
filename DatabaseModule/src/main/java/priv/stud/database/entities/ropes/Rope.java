package priv.stud.database.entities.ropes;

import lombok.*;
import org.hibernate.annotations.Type;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.warehouse.WarehouseRope;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "typeed") //W przypadku single table
@Entity(name = "ROPE")
public abstract class Rope{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private int elongation;

    private double diameter;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Type(type = "priv.stud.database.entities.ropes.RopeType")
    private Enum<RopeType> ropeType;

    @Embedded
    private BasicRopeInfo basicRopeInfo;

    @OneToMany(mappedBy = "rope")
    private Set<WarehouseRope> warehouseRopes;

    @OneToMany(mappedBy = "rope")
    private Set<OrderedModel> orderedModels;

    public Rope(String name, int elongation, double diameter, boolean isActive, BasicRopeInfo basicRopeInfo) {
        this.name = name;
        this.elongation = elongation;
        this.diameter = diameter;
        this.isActive = isActive;
        this.basicRopeInfo = basicRopeInfo;
    }
}
