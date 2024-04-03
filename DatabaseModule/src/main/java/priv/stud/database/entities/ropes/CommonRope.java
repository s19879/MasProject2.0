package priv.stud.database.entities.ropes;

import lombok.*;

import java.util.EnumSet;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "COMMON_ROPE")
//@DiscriminatorValue("COMMON") //W przypadku single_table
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class CommonRope extends Rope{


    private String ropeKind;
    CommonRope(String name, int elongation, double diameeter, RopeType ropeType, BasicRopeInfo basicRopeInfo){
        super(name, elongation, diameeter, ropeType, basicRopeInfo);
    }

    public CommonRope(String name, int elongation, double diameter, RopeType ropeType, BasicRopeInfo basicRopeInfo, String ropeKind) {
        super(name, elongation, diameter, ropeType, basicRopeInfo);
        this.ropeKind = ropeKind;
    }
}

