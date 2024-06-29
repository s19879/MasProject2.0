package priv.stud.forms;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.orders.OrderStatus;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.WarehouseRope;
import priv.stud.database.services.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    JPanel readyButtonPanel;
    private JTextField searchField;
    private JTextField quantityField;
    List<Rope> ropesInStock;
    private JPanel summaryPanel = new JPanel();

    DataInputPanel(MainForm mainForm){
        super(mainForm);

        ropeService = ServiceFactory.getRopeService();
        orderedModelService = ServiceFactory.getOrderedModelService();
        orderService = ServiceFactory.getOrderService();

        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.PAGE_AXIS));
        setTitle("Wprowadzanie danych");

        setLocalRopeArray();
        setInputFields();
        setOrderTable();
        setReadyButton();

        setContentPanel(summaryPanel);
    }

    private void setLocalRopeArray(){
        ropesInStock = mainForm.getWarehouse().getWarehouseRopes().stream()
                .filter(e -> e.getAmount() > 0)
                .map(WarehouseRope::getRope)
                .collect(Collectors.toList());

        this.ropeArray =  ropesInStock
                .stream()
                .map(Rope::getName)
                .toArray(String[]::new);
    }

    private void setInputFields(){
        JPanel searchFieldPanel = createNewPanel();
        searchFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        setComboBox();
        setSearchField();
        setQuantityField();
        JButton addButton = setAddButton();

        searchFieldPanel.add(createPanelWithLabel("Wyszukaj linę:", searchField, BoxLayout.X_AXIS));
        searchFieldPanel.add(createPanelWithLabel("Wybierz linę:", comboBox,BoxLayout.X_AXIS));
        searchFieldPanel.add(createPanelWithLabel("Ilość:", quantityField,BoxLayout.X_AXIS));
        searchFieldPanel.add(addButton);
        summaryPanel.add(searchFieldPanel);

    }

    private void setComboBox(){
        comboBox = new JComboBox<>(ropeArray);

        comboBox.setEditable(false);
        comboBox.setSelectedItem(null);
    }

    private void setSearchField(){
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 25));
        setSearchFieldListener();
    }

    private void setSearchFieldListener(){
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = searchField.getText().toLowerCase();

                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                for (String item : ropeArray) {
                    if (item.toLowerCase().startsWith(input)) {
                        model.addElement(item);
                    }
                }

                comboBox.setModel(model);
            }
        });
    }

    private void setQuantityField(){
        quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(50, 25));
    }

    private JButton setAddButton() {
        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(e -> {
            Object selectedRope = comboBox.getSelectedItem();
            if (selectedRope instanceof String && !quantityField.getText().isEmpty()) {
                String ropeName = (String) selectedRope;
                Rope rope = findRopeByName(ropeName);


                if (rope != null) {
                    int quantity = Integer.parseInt(quantityField.getText());
                    ropes.put(rope, quantity);
                    refreshTable();
                    comboBox.setSelectedItem(null);
                    quantityField.setText(null);
                    searchField.setText(null);
                }
            } else JOptionPane.showMessageDialog(null,
                    "Nie można dodać liny. Należy wybrać linę i podać ilość",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        });
        return addButton;
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
        orderTable.setFont(new Font(comboBox.getFont().getFontName(), Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderListPanel.add(scrollPane, BorderLayout.CENTER);
        summaryPanel.add(orderListPanel);
    }

    private void refreshTable() {
        summaryPanel.remove(orderListPanel);
        summaryPanel.remove(readyButtonPanel);
        summaryPanel.revalidate();
        setOrderTable();
        setReadyButton();
        summaryPanel.revalidate();
        summaryPanel.repaint();
    }

    private void setReadyButton(){
         readyButtonPanel = createNewPanel();
        JButton readyButton = new JButton("Gotowe");
        readyButton.addActionListener(e -> {
            if(!ropes.isEmpty()) {
                String verificationNote = "";
                boolean isReduced = false;

                Order order = orderService.addOrder(mainForm.getWarehouse(), mainForm.getStore());

                for (Map.Entry<Rope, Integer> entry : ropes.entrySet()) {
                    WarehouseRope warehouseRope = mainForm.getWarehouse().getWarehouseRopes().stream()
                            .filter(ropeInWorkshop -> ropeInWorkshop.getRope().equals(entry.getKey()))
                            .findFirst()
                            .orElse(null);//(workshop, entry.getKey());

                    if (warehouseRope != null && warehouseRope.getAmount() < entry.getValue()) {
                        verificationNote += "Lina o nazwie " + entry.getKey().getName() + " występuje w ilości "
                                + warehouseRope.getAmount() + " . W zamówieniu " + entry.getValue() + "\n";
                        entry.setValue(warehouseRope.getAmount());
                        isReduced = true;
                    }
                    orderedModelService.addOrderedModel(entry.getKey(), order, entry.getValue(), isReduced);
                }
                mainForm.setOrder(order);

                if (isReduced) {
                    orderService.changeStatus(order, OrderStatus.PENDING_APPROVAL);
                    mainForm.changePanel(mainForm.createCorrectionPanel(verificationNote));
                } else {
                    orderService.changeStatus(order, OrderStatus.OPEN);
                    mainForm.changePanel(mainForm.createComplementationPanel());
                }

            } else JOptionPane.showMessageDialog(null, "Brak wybranych lin", "Błąd", JOptionPane.ERROR_MESSAGE);
        });

        readyButtonPanel.add(readyButton);
        summaryPanel.add(readyButtonPanel);
    }
}
