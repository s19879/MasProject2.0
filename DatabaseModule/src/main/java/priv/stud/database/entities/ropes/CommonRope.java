package priv.stud.database.entities.ropes;

import lombok.*;

import java.util.EnumSet;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
//@DiscriminatorValue("COMMON") //W przypadku single_table
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class CommonRope extends Rope{
    @ElementCollection(targetClass = CommonRopeKind.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private EnumSet<CommonRopeKind> ropeKind;
}
