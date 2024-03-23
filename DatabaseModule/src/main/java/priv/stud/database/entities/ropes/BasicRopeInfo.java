package priv.stud.database.entities.ropes;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BasicRopeInfo {
    private double price;
    private int length;
    private int allowableWasteAmount;
    private int sheath;
    private String braidPattern;
    private double knoty;
    private boolean isWaterproof;
}
