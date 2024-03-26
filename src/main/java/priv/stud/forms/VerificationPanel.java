package priv.stud.forms;



import priv.stud.database.entities.stores.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VerificationPanel extends CustomPanel {
    public VerificationPanel(OrderMainForm orderMainForm){
        super(orderMainForm);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setTitle("Weryfikacja zamówienia");
        setStoreList();
        setButtons();
    }

    private void setStoreList(){
        JPanel storePanel = createNewPanel();
        JLabel storeLabel = new JLabel("Wybierz sklep: ");
        storePanel.add(storeLabel);

        JComboBox storeArray = new JComboBox<>(getAllStores().toArray());
        storeArray.setSize(100, 20);

        storePanel.add(storeArray);
        add(storePanel);

        storeArray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Store selectedStore = (Store) storeArray.getSelectedItem();
                orderMainForm.setStore(selectedStore);
            }
        });
    }

    private void setButtons(){
        JPanel buttonPanel = createNewPanel();
        JButton positiveButton = new JButton("Weryfikacja pozytywna");
        buttonPanel.add(positiveButton);

        JButton negativeButton = new JButton("Weryfikacja negatywna");
        buttonPanel.add(negativeButton);
        add(buttonPanel);
        positiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderMainForm.changePanel(orderMainForm.createDataInputPanel());
            }
        });

        negativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderMainForm.setStore(null);
                orderMainForm.changePanel(orderMainForm.createMaPanel());
            }
        });
    }
}