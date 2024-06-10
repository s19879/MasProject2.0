package priv.stud.forms;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.services.StoreService;
import priv.stud.database.services.StoreServiceImpl;
import priv.stud.database.services.WarehouseService;
import priv.stud.database.services.WarehouseServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MainPanel extends CustomPanel{
    StoreService storeService;
    public MainPanel(MainForm mainForm){
        super(mainForm);
        storeService = new StoreServiceImpl();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setTitle("Obsługa zamówień");
        setBackground(Color.BLUE);
        addStoreButton();
        addWarehouseServiceButton();
        setVisible(true);

        WarehouseService warehouseService = new WarehouseServiceImpl();
        mainForm.setWarehouse(warehouseService.getWarehouseById(1));
    }

    public void addStoreButton(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Żaden produkt nie został wybrany");
                Store store = storeService.findStoreById(3L);
                for(Map.Entry<Long, Order> entry : store.getOrdersQualif().entrySet()){
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
            }
        };
        add(createButton("Sklep zamówienie", actionListener));
    }

    public void addWarehouseServiceButton(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.changePanel(mainForm.createVerificationPanel());
                //JOptionPane.showMessageDialog(null, "Żaden produkt nie został wybrany");
            }
        };

        add(createButton("Obsługa zamówień magazyn" , actionListener));

    }
}
