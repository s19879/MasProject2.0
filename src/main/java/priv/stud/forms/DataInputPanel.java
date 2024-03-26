package priv.stud.forms;



import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.WarehouseRope;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class DataInputPanel extends CustomPanel {

    private Map<Rope, Integer> ropes = new HashMap<>();
    String[] ropeArray;
    private JTable orderTable;
    JComboBox<String> comboBox;
    JPanel orderListPanel;

    DataInputPanel(OrderMainForm orderMainForm){
        super(orderMainForm);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setTitle("Wprowadzanie danych");
        this.ropeArray = WarehouseRope.getAllRopesNameInWorkshop(orderMainForm.getWorkshop());
        setSearchField();
        setReadyButton();
        setOrderTable();

    }



    private void setSearchField(){
        JPanel searchFieldPanel = createNewPanel();

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));

        comboBox = new JComboBox<>(ropeArray);
        comboBox.setEditable(true);
        comboBox.setSelectedItem(null);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = textField.getText().toLowerCase();

                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                for (String item : ropeArray) {
                    if (item.toLowerCase().startsWith(input)) {
                        model.addElement(item);
                    }
                }

                comboBox.setModel(model);
            }
        });

        JTextField quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(50, 25));

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.add(new JLabel("Ilość:"));
        quantityPanel.add(quantityField);

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(e -> {
            Object selectedRope = comboBox.getSelectedItem();
            if (selectedRope != null && selectedRope instanceof String) {
                String ropeName = (String) selectedRope;
                Rope rope = findRopeByName(ropeName);
                if (rope != null) {
                    int quantity = Integer.parseInt(quantityField.getText());
                    ropes.put(rope, quantity);
                    refreshTable();
                }
            }
        });

        searchFieldPanel.add(textField);
        searchFieldPanel.add(comboBox);
        searchFieldPanel.add(quantityPanel);
        searchFieldPanel.add(addButton);
        add(searchFieldPanel);

    }

    private void setOrderTable() {
        orderListPanel = createNewPanel();
        orderListPanel.setLayout(new BorderLayout());
        orderListPanel.setPreferredSize(new Dimension(300, 200));

        String[] columnNames = {"Nazwa liny", "Ilość"};

        Object[][] rowData = new Object[ropes.size()][2];
        int i = 0;
        for (Map.Entry<Rope, Integer> entry : ropes.entrySet()) {
            rowData[i][0] = entry.getKey().getName();
            rowData[i][1] = entry.getValue();
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        orderTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderListPanel.add(scrollPane, BorderLayout.CENTER);
        add(orderListPanel);
    }

    private void refreshTable() {
        remove(orderListPanel);
        setOrderTable();
        revalidate();
        repaint();
    }

    private void setReadyButton(){
        JPanel readyButtonPanel = createNewPanel();
        JButton readyButton = new JButton("Gotowe");
        readyButton.addActionListener(e -> {

            String verificationNote = "";
            java.util.List<OrderedModel> orderedModelList = new ArrayList<>();
            Workshop workshop = orderMainForm.getWorkshop();
            Store store = orderMainForm.getStore();
            boolean isReduced = false;
            for(Map.Entry<Rope, Integer> entry : ropes.entrySet()){
                boolean isEnoughRopes = true;
                WorkshopLine workshopLine = WorkshopLine.getRopeInWorkshop(workshop, entry.getKey());

                if(workshopLine.getAmount() < entry.getValue()){
                    isEnoughRopes = false;
                    verificationNote += "Lina o nazwie " + entry.getKey().getName() + " występuje w ilości " + workshopLine.getAmount() + " . W zamówieniu " + entry.getValue() + "\n";
                    entry.setValue(workshopLine.getAmount());
                    isReduced = true;
                }

                orderedModelList.add(new OrderedModel(entry.getKey(), entry.getValue(), !isEnoughRopes ));
            }

            int idOrderStrore  = store.addOrder(workshop, orderedModelList);
            Order order = store.getOrderMapQualif().get(idOrderStrore);
            if(isReduced == true){
                order.changeStatus(OrderStatus.PENDING_APPROVAL);
                orderMainForm.changePanel(orderMainForm.createCorrectionPanel(ropes, verificationNote, idOrderStrore));
            }
            else{
                order.changeStatus(OrderStatus.OPEN);
                orderMainForm.changePanel(orderMainForm.createComplementationPanel(idOrderStrore));
            }

        });

        readyButtonPanel.add(readyButton);
        add(readyButtonPanel);
    }
}