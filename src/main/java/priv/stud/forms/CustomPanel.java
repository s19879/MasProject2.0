package priv.stud.forms;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    protected OrderMainForm orderMainForm;

    CustomPanel(OrderMainForm orderMainForm){
        this.orderMainForm = orderMainForm;
    }
    protected void setTitle(String title){
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel(title);
        Font font = new Font(titleLabel.getFont().getName(), Font.BOLD, 20);
        titleLabel.setFont(font);
        titleLabel.setSize(400, 300);
        titlePanel.add(titleLabel);
        add(titlePanel);
    }

    protected JPanel createNewPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        return panel;
    }


}