package priv.stud.database.entities.warehouse;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@Setter
@Embeddable
public class PersonalWorkerData {
    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String lastName;

    @NonNull
    @Column(nullable = false)
    private String pesel;

    @NonNull
    @Column(nullable = false)
    private Date birthDate;
}
