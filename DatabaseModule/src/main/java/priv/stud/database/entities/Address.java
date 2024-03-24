package priv.stud.database.entities;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {
    @NonNull
    @Column(nullable = false)
    private String city;

    @NonNull
    @Column(nullable = false)
    private String street;

    @NonNull
    @Column(nullable = false)
    private String streetNumber;

    @NonNull
    @Column(nullable = false)
    private String zipCode;
}
