package priv.stud.forms;

import priv.stud.database.entities.stores.Store;
import priv.stud.database.services.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerificationPanel extends CustomPanel {

    private StoreService storeService;
    private JPanel summaryPanel = new JPanel();
    JComboBox storeArray;
    public VerificationPanel(MainForm mainForm){
        super(mainForm);
        storeService = ServiceFactory.getStoreService();

        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setTitle("Weryfikacja zamówienia");
        setStoreList();
        setButtons();
        setContentPanel(summaryPanel);
    }

    private void setStoreList() {
        JPanel storePanel = createNewPanel();
        JLabel storeLabel = new JLabel("Wybierz sklep: ");
        storePanel.add(storeLabel);

        storeArray = new JComboBox<>(storeService.findAllStores().toArray());
        storeArray.setSelectedItem(null);
        storeArray.setSize(100, 20);

        storePanel.add(storeArray);
        summaryPanel.add(storePanel);

        storeArray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Store selectedStore = (Store) storeArray.getSelectedItem();
                mainForm.setStore(selectedStore);
            }
        });
    }

    private void setButtons(){
        JPanel buttonPanel = createNewPanel();
        JPanel positiveButtonPanel = createButtonPanel("Weryfikacja pozytywna", e -> {
            if(storeArray.getSelectedItem() != null)
                mainForm.changePanel(mainForm.createDataInputPanel());
            else JOptionPane.showMessageDialog(null, "Brak wybranego sklepu", "Błąd", JOptionPane.ERROR_MESSAGE);
        });
        positiveButtonPanel.setBackground(Color.GREEN);
        buttonPanel.add(positiveButtonPanel);



        JPanel negativeButtonPanel = createButtonPanel("Weryfikacja negatywna", e -> {
            if(storeArray.getSelectedItem() != null)
                setNegativeConfirmationPopup();
            else JOptionPane.showMessageDialog(null, "Brak wybranego sklepu", "Błąd", JOptionPane.ERROR_MESSAGE);
        });
        negativeButtonPanel.setBackground(Color.RED);
        buttonPanel.add(negativeButtonPanel);
        summaryPanel.add(buttonPanel);
    }

    private void setNegativeConfirmationPopup(){
        String[] options = {"Tak", "Nie"};
        int result = JOptionPane.showOptionDialog(this,
                "Czy potwierdzasz weryfikację negatywną?",
                "Potwierdzenie weryfikacji negatywnej",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if(result == JOptionPane.YES_OPTION){
            setNegativeReasonPopup();
        }
    }

    private void setNegativeReasonPopup(){
        JPanel negativeReasonPanel = createNewPanel();
        negativeReasonPanel.setLayout(new BoxLayout(negativeReasonPanel, BoxLayout.Y_AXIS));
        JTextArea textAreaInput = new JTextArea(8, 15);
        negativeReasonPanel.add(new JLabel("Podaj powód negatywnej weryfikacji"));
        negativeReasonPanel.add(new JScrollPane(textAreaInput));
        String[] options = {"Zatwierdź", "Anuluj"};

        int result = JOptionPane.showOptionDialog(this,
                negativeReasonPanel,
                "Powód negatywnej weryfikacji",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if(result == JOptionPane.YES_OPTION){
            System.out.println("Wysyłany mail o treści " + textAreaInput.getText());
            mainForm.setStore(null);
            mainForm.changePanel(mainForm.createMainPanel());
        }
    }
}
