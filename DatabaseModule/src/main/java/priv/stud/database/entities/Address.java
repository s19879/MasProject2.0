package priv.stud.database.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
public class Address {
    private String city;
    private String street;
    private String streetNumber;
    private String zipCode;
}
