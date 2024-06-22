package priv.stud.database.entities.warehouse;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ACCOUNTANT")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Accountant extends Worker{
    private String education;

    public Accountant(@NonNull String name, @NonNull String lastName, @NonNull String pesel,
                      @NonNull Date birthDate, @NonNull String education){
        super(name, lastName, pesel, birthDate);
        this.education = education;
    }

}
