package priv.stud.forms;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.services.OrderService;
import priv.stud.database.services.OrderServiceImpl;
import priv.stud.database.services.ServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectionPanel extends CustomPanel{
    private String verficationNote;
    private OrderService orderService;
    private JPanel summaryPanel = new JPanel();
    protected CorrectionPanel(MainForm mainForm, String verificationNote) {
        super(mainForm);
        orderService = ServiceFactory.getOrderService();
        setTitle("Akceptacja korekcji zamówienia");
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));
        this.verficationNote = verificationNote;
        setStoreData();
        setOutputText();
        setButton();
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
    private void setOutputText(){
        String outputText = verficationNote;


        JPanel panel = createNewPanel();
        JTextArea outputTextArea = new JTextArea(20, 45);
        outputTextArea.setFont(new Font(outputTextArea.getFont().getName(), Font.BOLD, 12));
        outputTextArea.setEditable(false);
        outputTextArea.setText(outputText);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        panel.add(scrollPane);
        summaryPanel.add(panel);

    }

    private void setButton(){
        JPanel buttonPanel = createNewPanel();
        JButton acceptationButton = new JButton("Akceptacja sklepu");
        acceptationButton.setBackground(Color.GREEN);
        JButton noAcceptButton = new JButton("Brak akceptacji");
        noAcceptButton.setBackground(Color.RED);
        buttonPanel.add(acceptationButton);
        buttonPanel.add(noAcceptButton);
        summaryPanel.add(buttonPanel);

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
