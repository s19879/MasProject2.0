package priv.stud.database.entities.stores;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "EXTERNAL_STORE")
public class ExternalStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double loyality;

    @OneToOne(mappedBy = "externalStore", cascade = CascadeType.ALL)
    private Store store;

    public ExternalStore(double loyality){
        this.loyality = loyality;
    }
}
