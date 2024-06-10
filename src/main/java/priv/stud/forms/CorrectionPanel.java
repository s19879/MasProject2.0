package priv.stud.forms;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.services.OrderService;
import priv.stud.database.services.OrderServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectionPanel extends CustomPanel{
    private String verficationNote;
    private OrderService orderService;
    protected CorrectionPanel(MainForm mainForm, String verificationNote) {
        super(mainForm);
        orderService = new OrderServiceImpl();
        setTitle("Akceptacja korekcji zamówienia");
        this.verficationNote = verificationNote;
        setOutputText();
        setButton();
    }

    private void setOutputText(){
        Store store = mainForm.getStore();
        String outputText = "Firma: " + store.getName() + " " + store.getAddress() + "\n";
        for(OrderedModel model : mainForm.getOrder().getOrderedModels()){
            if(model.isReducedValue()) outputText += "Lina " + model.getRope().getName()
                    + " została zredukowana do ilości " + model.getAmount();
        }

        JPanel panel = createNewPanel();
        JTextArea outputTextArea = new JTextArea(20, 45);
        outputTextArea.setText(outputText);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        panel.add(scrollPane);
        add(panel);

    }

    private void setButton(){
        JPanel buttonPanel = createNewPanel();
        JButton acceptationButton = new JButton("Zostało zaakceptowane");
        JButton noAcceptButton = new JButton("Nie zostało zaakceptowane");
        buttonPanel.add(acceptationButton);
        buttonPanel.add(noAcceptButton);
        add(buttonPanel);

        acceptationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {;
                orderService.changeStatus(mainForm.getOrder(), OrderStatus.OPEN);
                mainForm.changePanel(mainForm.createComplementationPanel());
            }
        });

        noAcceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.changeStatus(mainForm.getOrder(), OrderStatus.CANCELLED);
                mainForm.setStore(null);
                mainForm.setOrder(null);
                mainForm.changePanel(mainForm.createMainPanel());
            }
        });
    }
}
