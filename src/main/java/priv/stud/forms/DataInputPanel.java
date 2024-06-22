package priv.stud.forms;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.orders.OrderedModel;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.stores.Store;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.services.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataInputPanel extends CustomPanel {

    RopeService ropeService;
    OrderedModelService orderedModelService;
    OrderService orderService;

    private Map<Rope, Integer> ropes = new HashMap<>();
    String[] ropeArray;
    private JTable orderTable;
    JComboBox<String> comboBox;
    JPanel orderListPanel;

    List<Rope> ropesInStock;

    DataInputPanel(MainForm mainForm){
        super(mainForm);
        ropeService = ServiceFactory.getRopeService();
        orderedModelService = ServiceFactory.getOrderedModelService();
        orderService = ServiceFactory.getOrderService();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setTitle("Wprowadzanie danych");
        ropesInStock = mainForm.getWarehouse().getWarehouseRopes().stream()
                .filter(e -> e.getAmount() > 0)
                .map(WarehouseRope::getRope)
                .collect(Collectors.toList());

        this.ropeArray =  ropesInStock
                .stream()
                .map(Rope::getName)
                .toArray(String[]::new);

        setInputFields();
        setReadyButton();
        setOrderTable();

    }



    private void setInputFields(){
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


                if (rope != null ) {
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

    private Rope findRopeByName(String name){
        return ropesInStock.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
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
            boolean isReduced = false;

            Order order = orderService.addOrder(mainForm.getWarehouse(), mainForm.getStore());

            for(Map.Entry<Rope, Integer> entry : ropes.entrySet()){
                WarehouseRope warehouseRope = mainForm.getWarehouse().getWarehouseRopes().stream()
                        .filter(ropeInWorkshop -> ropeInWorkshop.getRope().equals(entry.getKey()) )
                        .findFirst()
                        .orElse(null);//(workshop, entry.getKey());

                if(warehouseRope.getAmount() < entry.getValue()){
                    verificationNote += "Lina o nazwie " + entry.getKey().getName() + " występuje w ilości "
                            + warehouseRope.getAmount() + " . W zamówieniu " + entry.getValue() + "\n";
                    entry.setValue(warehouseRope.getAmount());
                    isReduced = true;
                }
                orderedModelService.addOrderedModel(entry.getKey(), order, entry.getValue(), isReduced);
            }
            mainForm.setOrder(order);

            if(isReduced){
                orderService.changeStatus(order, OrderStatus.PENDING_APPROVAL);
                mainForm.changePanel(mainForm.createCorrectionPanel(verificationNote));
            } else {
                orderService.changeStatus(order, OrderStatus.OPEN);
                mainForm.changePanel(mainForm.createComplementationPanel());
            }

        });

        readyButtonPanel.add(readyButton);
        add(readyButtonPanel);
    }
}
