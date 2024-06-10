package priv.stud.database.entities.stores;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "COMPANY_STORE")
public class CompanyStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nameOfManager;
    private int employeesNumber;

    @OneToOne(mappedBy = "companyStore", cascade = CascadeType.ALL)
    private Store store;

    public CompanyStore(@NonNull String nameOfManager, int employeesNumber){
        this.nameOfManager = nameOfManager;
        this.employeesNumber = employeesNumber;
    }
}
