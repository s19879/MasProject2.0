package priv.stud.forms;

import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.services.OrderService;
import priv.stud.database.services.OrderServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompletationPanel extends CustomPanel{

    private final OrderService orderService;

    CompletationPanel(MainForm mainForm){
        super(mainForm);
        orderService = new OrderServiceImpl();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setTitle("Kompletacja zamówienia");
        setOrderText();
        setButtons();
    }

    private void setOrderText(){
        Store store = mainForm.getStore();
        String outputText = "Firma: " +store.getName() + " " + store.getAddress() + "\n";
        for(OrderedModel model : mainForm.getOrder().getOrderedModels()){
            outputText += model.toString();
        }

        JPanel panel = createNewPanel();
        JTextArea outputTextArea = new JTextArea(20, 45);
        outputTextArea.setText(outputText);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        panel.add(scrollPane);
        add(panel);
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
        add(panel);
        completationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.changeStatus(mainForm.getOrder(),OrderStatus.IN_PROGRESS);
                panel.remove(completationButton);
                panel.add(doneButton);
                revalidate();
                repaint();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.changeStatus(mainForm.getOrder(),OrderStatus.DONE);
//                for(OrderedModel model : order.getOrderedModels()){
//                    WorkshopLine workshopLine = WorkshopLine.getRopeInWorkshop(order.getWorkshop(), model.getRope());
//                    workshopLine.setAmount(workshopLine.getAmount() - model.getAmount());
//                }
//                orderMainForm.setStore(null);
//                orderMainForm.changePanel(orderMainForm.createMaPanel());
            }
        });

    }



}