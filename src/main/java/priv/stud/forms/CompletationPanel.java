package priv.stud.forms;

import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.services.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompletationPanel extends CustomPanel{

    private final OrderService orderService;
    private final WarehouseRopeService warehouseRopeService;
    private final WarehouseService warehouseService;
    private JPanel summaryPanel = new JPanel();

    CompletationPanel(MainForm mainForm){
        super(mainForm);
        orderService = ServiceFactory.getOrderService();
        warehouseRopeService = ServiceFactory.getWarehouseRopeService();
        warehouseService = ServiceFactory.getWarehouseService();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));
        setTitle("Kompletacja zamówienia");
        setStoreData();
        setOrderText();
        setButtons();
        setContentPanel(summaryPanel);
    }

    private void setStoreData(){
        JPanel storePanel = createNewPanel();
        JLabel storeLabel = new JLabel("Dane sklepu: " + mainForm.getStore().toString());
        Font font = new Font(storeLabel.getFont().getName(), Font.BOLD, 16);
        storeLabel.setFont(font);
        storePanel.add(storeLabel);
        summaryPanel.add(storePanel);
    }
    private void setOrderText(){
        Store store = mainForm.getStore();
        String outputText = "";
        for(OrderedModel model : mainForm.getOrder().getOrderedModels()){
            outputText += model.toString() + "\n";
        }

        JPanel panel = createNewPanel();
        JTextArea outputTextArea = new JTextArea(20, 45);
        outputTextArea.setFont(new Font(outputTextArea.getFont().getName(), Font.BOLD, 12));
        outputTextArea.setText(outputText);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        panel.add(scrollPane);
        summaryPanel.add(panel);
    }

    private void setButtons(){
        JPanel panel = createNewPanel();
        JButton completationButton = new JButton("Rozpoczęcie przygotowania produktu");
        JButton doneButton = new JButton("Produkt wysłany");


        OrderStatus orderStatus = mainForm.getOrder().getStatus();
        if(orderStatus.equals(OrderStatus.OPEN)){
            panel.add(completationButton);
        } else if(orderStatus.equals(OrderStatus.IN_PROGRESS)){
            panel.add(doneButton);
        }
        summaryPanel.add(panel);
        completationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.changeStatus(mainForm.getOrder(),OrderStatus.IN_PROGRESS);
                panel.remove(completationButton);
                panel.add(doneButton);
                summaryPanel.revalidate();
                summaryPanel.repaint();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.changeStatus(mainForm.getOrder(),OrderStatus.DONE);
                Warehouse warehouse = mainForm.getWarehouse();
                for(OrderedModel model : mainForm.getOrder().getOrderedModels()){
                        WarehouseRope warehouseRope = warehouseRopeService.getWarehouseRope(model.getRope(), warehouse);
                        int updatedAmount = warehouseRope.getAmount() - model.getAmount();
                        warehouseRope.setAmount(updatedAmount);
                        warehouseRopeService.saveWarehouseRope(warehouseRope);
                        mainForm.setWarehouse(
                                warehouseService.getWarehouseById(warehouse.getId())
                        );
                }
                mainForm.changePanel(mainForm.createMainPanel());
                mainForm.setStore(null);
                mainForm.setOrder(null);
            }
        });

    }



}