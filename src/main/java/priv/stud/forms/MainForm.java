package priv.stud.forms;

import lombok.Getter;
import lombok.Setter;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame{
    protected JPanel currentPanel;

    @Getter
    @Setter
    private Store store;

    @Getter
    @Setter
    private Warehouse warehouse;

    @Getter
    @Setter
    private Order order;

    public MainForm(){
        super("Obsługa zamówień");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        changePanel(createMainPanel());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public JPanel createMainPanel(){
        return new MainPanel(this);
    }

    public JPanel createVerificationPanel(){ return new VerificationPanel(this);}

    public void changePanel(JPanel newPanel) {
        currentPanel = newPanel;
        setContentPane(currentPanel);
        invalidate();
        validate();
    }

    public JPanel createDataInputPanel() { return new DataInputPanel(this);}

    public JPanel createCorrectionPanel(String verificationNote) { return  new CorrectionPanel(this, verificationNote);}

    public JPanel createComplementationPanel() { return  new CompletationPanel(this); }

}
