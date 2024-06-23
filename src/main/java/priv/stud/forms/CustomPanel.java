package priv.stud.forms;

import lombok.NonNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomPanel extends JPanel {

    protected MainForm mainForm;


    protected CustomPanel(@NonNull  MainForm mainForm){
        this.mainForm = mainForm;
        setBackground(Color.DARK_GRAY);
        //Font font = new Font("")
    }


    protected void setTitle(String title){
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel(title);
        Font font = new Font(titleLabel.getFont().getName(), Font.BOLD, 20);
        titleLabel.setFont(font);
        titleLabel.setSize(400, 100);
        titlePanel.add(titleLabel);
        add(titlePanel);
    }

    protected JPanel createNewPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        return panel;
    }

    protected JPanel createButtonPanel(String buttonText,  ActionListener actionListener){
        JPanel panel = new JPanel();
        JButton button = new JButton(buttonText);
        panel.add(button);
        button.addActionListener(actionListener);
        button.setVisible(true);
        return panel;
    }

    protected JPanel createPanelWithLabel(String label, JComponent component, int axis){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,axis));
        JLabel labelField = new JLabel(label);
        panel.add(labelField);
        panel.add(component);
        return panel;
    }
}
