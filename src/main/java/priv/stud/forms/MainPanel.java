package priv.stud.forms;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.services.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MainPanel extends CustomPanel{
    StoreService storeService;
    public MainPanel(MainForm mainForm){
        super(mainForm);
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        storeService = ServiceFactory.getStoreService();
        WarehouseService warehouseService = ServiceFactory.getWarehouseService();
        mainForm.setWarehouse(warehouseService.getWarehouseById(1));

        setTitle("Obsługa zamówień");
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        JPanel buttonPannel = new JPanel();
        buttonPannel.setSize(400,100);
        buttonPannel.setLayout(new GridLayout(2,1));
        buttonPannel.setBackground(Color.BLUE);
        addStoreButton(buttonPannel);
        addWarehouseServiceButton(buttonPannel);
        summaryPanel.add(buttonPannel);
        setContentPanel(summaryPanel);
        setVisible(true);
    }

    public void addStoreButton(JPanel panel){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // JOptionPane.showMessageDialog(null, "Żaden produkt nie został wybrany");
                Store store = storeService.findStoreById(1L);
                for(Map.Entry<Long, Order> entry : store.getOrdersQualif().entrySet()){
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
            }
        };
        panel.add(createButtonPanel("Moduł sklepu", actionListener));
    }

    public void addWarehouseServiceButton(JPanel panel){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.changePanel(mainForm.createVerificationPanel());
            }
        };
        panel.add(createButtonPanel("Moduł magazynu" , actionListener));

    }
}
