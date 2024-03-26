package priv.stud.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends CustomPanel {
    public MainPanel(OrderMainForm orderMainForm){
        super(orderMainForm);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setTitle("Panel magazynu");
        setNewOrderButton();
        setVisible(true);
    }

    private void setNewOrderButton(){
        JPanel panel = createNewPanel();
        JButton newOrderButton = new JButton("Nowe zamówienie");
        panel.add(newOrderButton);
        add(panel);
        newOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderMainForm.changePanel(orderMainForm.createVerPanel());
            }
        });
    }

}