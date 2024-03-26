package priv.stud.forms;

import lombok.Getter;
import lombok.Setter;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;


import javax.swing.*;
import java.util.Map;

public class OrderMainForm  extends JFrame{

    protected JPanel currentPanel;

    @Getter
    @Setter
    private Store store;

    @Getter
    private Warehouse workshop;

    public OrderMainForm(Warehouse workshop){
        super("Panel magazynu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        changePanel(createMaPanel());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.workshop = workshop;
    }

    protected JPanel createMaPanel(){
        return new MainPanel(this);
    }

    protected JPanel createVerPanel(){
        return new VerificationPanel(this);
    }

    protected JPanel createDataInputPanel(){
        return new DataInputPanel(this);
    }

    protected JPanel createCorrectionPanel(Map<Rope, Integer> ropes, String verificationNote, int idStoreOrder) {
        return  new CorrectionPanel(this, ropes, verificationNote, idStoreOrder);
    }

    protected JPanel createComplementationPanel(int idStoreOrder) {
        return  new CompletationPanel(this, idStoreOrder);
    }


    protected void changePanel(JPanel newPanel) {
        currentPanel = newPanel;
        setContentPane(currentPanel);
        invalidate();
        validate();
    }
}