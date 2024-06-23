package priv.stud.database.entities.warehouse;

import lombok.*;
import priv.stud.database.entities.Address;

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

    public Accountant(@NonNull PersonalWorkerData personalWorkerData, @NonNull Address address, @NonNull String education){
        super(personalWorkerData, address);
        this.education = education;
    }

}
