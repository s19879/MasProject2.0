package priv.stud.forms;

import lombok.NonNull;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.services.ServiceFactory;
import priv.stud.database.services.StoreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class StoreOrdersStatusPanel extends CustomPanel{
    private JPanel summaryPanel = new JPanel();
    JComboBox storeCombo;
    StoreService storeService;
    protected StoreOrdersStatusPanel(@NonNull MainForm mainForm) {
        super(mainForm);
        storeService = ServiceFactory.getStoreService();

        setTitle("Status zgłoszeń sklepów");
        summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));
        summaryPanel.setPreferredSize(new Dimension(300, 400));
        
        setStorePanel(summaryPanel);
        setReturnButton(summaryPanel);
        setContentPanel(summaryPanel);
    }

    private void setStorePanel(JPanel summaryPanel) {
        JPanel storePanel = createNewPanel();
        storeCombo =  new JComboBox<>(storeService.findAllStores().toArray());
        storeCombo.setSelectedItem(null);
        storeCombo.setSize(100,20);
        summaryPanel.add(storeCombo);

        JTextArea ordersArea = new JTextArea();
        ordersArea.setEditable(false);

        storeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Store selectedStore = (Store) storeCombo.getSelectedItem();
                if(selectedStore != null){
                    String orders = "";
                    for(Map.Entry<Long, Order> entry : selectedStore.getOrdersQualif().entrySet()){
                        orders += entry.getKey() + " " + entry.getValue() + "\n";
                    }
                    ordersArea.setText(orders);
                }
            }
        });
        storePanel.add(storeCombo);
        summaryPanel.add(storePanel);
        summaryPanel.add(ordersArea);

    }

    private void setReturnButton(JPanel summaryPanel) {
        JButton returnButton = new JButton("Powrót");
        summaryPanel.add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.changePanel(mainForm.createMainPanel());
            }
        });
    }
}
