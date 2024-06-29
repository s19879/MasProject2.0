package priv.stud.forms;

import lombok.NonNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * CustomPanel jest klasą nadrzędną wszystkich paneli wykorzystywanych w aplikacji.
 * Posiada szkielet układu ekranu oraz dodatkowe użyteczne metody, które w znacznej części pozwalają ograniczyć ilość
 * kodu.
 * Podczas dziedziczenia po klasie, należy pamiętać, żeby wykorzystywać metody setContentPanel() oraz setTitle()
 *
 * @@author Przemysław Mizgała
 */
public abstract class CustomPanel extends JPanel {

    protected MainForm mainForm;
    JPanel topPanel;
    JPanel contentPanel;
    JPanel bottomPanel;

    /**
     * Wywołany z klas dziedziczących, tworzy szkielet strony oraz pozwala na dostęp do metod
     *
     * @param mainForm - służy do uaktalniania/pobierania obiektów przetrzymywanych na MainForm oraz pozwala na dostęp
     *                 do metody zmieniającej ekrany
     */
    protected CustomPanel(@NonNull  MainForm mainForm){
        this.mainForm = mainForm;
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800,600));

        topPanel = createVerticalPanel(0.1);
        contentPanel = createVerticalPanel(0.8);
        bottomPanel = createVerticalPanel(0.1);

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    }

    /**
     * Służy do pozyskania JPanel o określonej szerokości w pionie
     *
     * @param proportion procentowa reprezentacja żądanej zajętości
     * @return zwraca JPanel o zadanej zajętości
     */

    private JPanel createVerticalPanel(double proportion) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        int height = (int) (getPreferredSize().height * proportion);
        panel.setPreferredSize(new Dimension(getPreferredSize().width, height));
        return panel;
    }

    /**
     * Ustawia tytuł w górnym panelu
     *
     * @param title
     */
    protected void setTitle(String title){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.CYAN);
        titlePanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel(title);
        Font font = new Font(titleLabel.getFont().getName(), Font.BOLD, 20);
        titleLabel.setFont(font);
        titlePanel.add(titleLabel);
        topPanel.add(titlePanel);
    }

    /**
     * Metoda mająca na celu wyświetlanie zawartości w środkowym panelu
     *
     * @param contentPanel JPanel który ma być umieszczony w contentPanel
     */
    void setContentPanel(JPanel contentPanel){
        JPanel summaryPanel = new JPanel();
        contentPanel.add(summaryPanel);
        Font font = new Font(contentPanel.getFont().getName(), Font.PLAIN, 15);
        contentPanel.setFont(font);
        this.contentPanel.add(contentPanel);
    }

    /**
     * Metoda służąca do utworzenia nowego panelu
     *
     * @return zwraca nowy panel
     * @deprecated Metoda służyła do tworzenia nowego panelu, ale z uwagi na zmianę koncepcji z layoutami obecnie jest bezużyteczna
     */
    protected JPanel createNewPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        return panel;
    }

    /**
     * Metoda służy do utworzenia przycisku, który jednocześnie określa napis, jak i przekazuję akcję do wykonania
     *
     * @param buttonText tekst w przycisku
     * @param actionListener akcja przy wciśnięciu przycisku
     * @return przycisk zwracany jest w panelu
     */
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
