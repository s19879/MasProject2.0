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
    CommonRope(String name, int elongation, double diameeter, BasicRopeInfo basicRopeInfo, String ropeKind){
        super(name, elongation, diameeter, true, basicRopeInfo);
    }

    public CommonRope(String name, int elongation, double diameter, boolean isActive, BasicRopeInfo basicRopeInfo, String ropeKind) {
        super(name, elongation, diameter, isActive, basicRopeInfo);
        this.ropeKind = ropeKind;
    }
}

