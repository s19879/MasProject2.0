package priv.stud.database.entities.ropes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TWIN_ROPE")
//@DiscriminatorValue("COMMON") //W przypadku single_table
@PrimaryKeyJoinColumn(referencedColumnName = "id")

public class TwinRope extends Rope{


    public TwinRope(String name, int elongation, double diameter, RopeType ropeType, BasicRopeInfo basicRopeInfo, boolean isDesigneForIceClimbing) {
        super(name, elongation, diameter, ropeType, basicRopeInfo);
        this.isDesigneForIceClimbing = isDesigneForIceClimbing;
    }

    private boolean isDesigneForIceClimbing;
}
