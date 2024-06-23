package priv.stud.forms;

import lombok.Getter;
import lombok.Setter;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    protected JPanel currentPanel;

    @Getter
    @Setter
    private Store store;

    @Getter
    @Setter
    private Warehouse warehouse;

    @Getter
    @Setter
    private Worker worker;

    @Getter
    @Setter
    private Order order;

    public MainForm(){
        super("Obsługa zamówień");
        setSize(800, 600);
        setLocationRelativeTo(null);

        if(worker != null && warehouse != null)
            changePanel(createMainPanel());
        else changePanel(createLoginPanel());

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CustomPanel createMainPanel(){
        return new MainPanel(this);
    }

    public CustomPanel createVerificationPanel(){
        return new VerificationPanel(this);}

    public CustomPanel createDataInputPanel() {
        return new DataInputPanel(this);}

    public CustomPanel createCorrectionPanel(String verificationNote) { return  new CorrectionPanel(this, verificationNote);}

    public CustomPanel createComplementationPanel() { return new CompletationPanel(this); }

    public LoginPanel createLoginPanel(){ return new LoginPanel(this); }

    public void changePanel(CustomPanel newPanel) {
        currentPanel = newPanel;
        setContentPane(currentPanel);
        invalidate();
        validate();
    }
}
