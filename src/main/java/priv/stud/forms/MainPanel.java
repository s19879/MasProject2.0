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
        storeService = ServiceFactory.getStoreService();
        WarehouseService warehouseService = ServiceFactory.getWarehouseService();

        setTitle("Obsługa zamówień");
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        JPanel buttonPannel = new JPanel();
        buttonPannel.setSize(400,100);
        buttonPannel.setLayout(new GridLayout(2,1));
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
                mainForm.changePanel(mainForm.createStoreOrdersStatusPanel());
            }
        };
        panel.add(createButtonPanel("Moduł sklepu (podgląd statusów)", actionListener));
    }

    public void addWarehouseServiceButton(JPanel panel){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.changePanel(mainForm.createVerificationPanel());
            }
        };
        panel.add(createButtonPanel("Moduł magazynu (przypadek użycia)" , actionListener));

    }
}
