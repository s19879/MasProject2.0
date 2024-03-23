package priv.stud.database.entities.ropes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import priv.stud.database.entities.warehouse.WorkshopLine;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "typeed") //W przypadku single table
@Entity(name = "ROPE")
public class Rope{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int elongation;

    private double diameter;

    private boolean isActive;


    @Enumerated(EnumType.STRING)
    @Type(type = "priv.stud.database.entities.ropes.RopeType")
    private Enum<RopeType> ropeType;

    @Embedded
    private BasicRopeInfo basicRopeInfo;

    @OneToMany(mappedBy = "workshop")
    private Set<WorkshopLine> workshopLines;
}
