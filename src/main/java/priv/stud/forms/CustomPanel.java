package priv.stud.forms;

import lombok.NonNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public  class CustomPanel extends JPanel {

    protected MainForm mainForm;
    JPanel topPanel;
    JPanel middlePanel;
    JPanel bottomPanel;

    protected CustomPanel(@NonNull  MainForm mainForm){
        this.mainForm = mainForm;
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800,600));

        topPanel = createVerticalPanel(0.1);
        middlePanel = createVerticalPanel(0.8);;
        bottomPanel = createVerticalPanel(0.1);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createVerticalPanel(double proportion) {
        JPanel panel = new JPanel();
//        panel.setOpaque(false); // Przezroczysty, żeby nie przeszkadzał w kolorze tła głównego panelu
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        int height = (int) (getPreferredSize().height * proportion);
        panel.setPreferredSize(new Dimension(getPreferredSize().width, height));
        return panel;
    }

    void setContentPanel(JPanel contentPanel){
        JPanel summaryPanel = new JPanel();
        //summaryPanel.setPreferredSize(new Dimension(400,600));
        contentPanel.add(summaryPanel);
        middlePanel.add(contentPanel);
    };


    protected void setTitle(String title){
        JPanel titlePanel = new JPanel();
        //titlePanel.setBackground(Color.BLUE);
        titlePanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel(title);
        Font font = new Font(titleLabel.getFont().getName(), Font.BOLD, 20);
        titleLabel.setFont(font);
        titlePanel.add(titleLabel);
        topPanel.add(titlePanel);
//        topPanel.invalidate();
//        topPanel.validate();
//        topPanel.repaint();
//        invalidate();
//        validate();
//        repaint();
    }

    protected JPanel createNewPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
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
