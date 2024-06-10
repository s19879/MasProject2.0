package priv.stud.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomPanel extends JPanel {

    protected MainForm mainForm;

    protected CustomPanel(MainForm mainForm){
        this.mainForm = mainForm;
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

    protected JPanel createButton(String buttonText,  ActionListener actionListener){
        JPanel panel = new JPanel();
        JButton button = new JButton(buttonText);
        panel.add(button);
        button.addActionListener(actionListener);
        panel.add(button);
        return panel;
    }
}
