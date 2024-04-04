package priv.stud.database.entities.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import priv.stud.database.entities.ropes.Rope;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ORDERED_MODEL")
public class OrderedModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int amount;
    private boolean isReducedValue;
    private static int minAmountToGetPromotion = 5;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Rope rope;

    public OrderedModel(Rope rope, Order order, int amount, boolean isReducedValue){
        this.rope = rope;
        this.order = order;
        this.amount = amount;
        this.isReducedValue = isReducedValue;
    }
}
