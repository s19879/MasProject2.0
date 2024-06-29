package priv.stud.forms;

import lombok.NonNull;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.Worker;
import priv.stud.database.services.ServiceFactory;
import priv.stud.database.services.WarehouseService;
import priv.stud.database.services.WorkerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginPanel extends CustomPanel{

    WarehouseService warehouseService;
    WorkerService workerService;

    JComboBox workshopCombo;
    JComboBox workerCombo;
    protected LoginPanel(@NonNull MainForm mainForm) {
        super(mainForm);
        warehouseService = ServiceFactory.getWarehouseService();
        workerService = ServiceFactory.getWorkerService();

        setTitle("Panel logowania");

        JPanel summaryPannel = new JPanel();
        summaryPannel.setLayout(new BoxLayout(summaryPannel, BoxLayout.PAGE_AXIS));
        summaryPannel.setPreferredSize(new Dimension(300, 400));
        setWarehouseList(summaryPannel);
        setValidationButton(summaryPannel);

        setContentPanel(summaryPannel);

    }

    private void setWarehouseList(JPanel summaryPanel) {
        JPanel workshopsPanel = createNewPanel();
        JLabel WorkshopLabel = new JLabel("Wybierz magazyn: ");
        workshopsPanel.add(WorkshopLabel);

         workshopCombo =  new JComboBox<>(warehouseService.findAllWarehouses().toArray());
        workshopCombo.setSelectedItem(null);
        workshopCombo.setSize(100,20 );
        workshopsPanel.add(workshopCombo);

        JPanel workerPanel = createNewPanel();
        JLabel workerLabel = new JLabel("Wybierz pracownika: ");
        workerPanel.add(workerLabel);

        workerCombo = new JComboBox<>();
        workerCombo.setPreferredSize(new Dimension(200, 20));
        workerPanel.add(workerCombo);
        workerPanel.setVisible(false);

        workshopCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Warehouse selectedWarehouse = (Warehouse) workshopCombo.getSelectedItem();
                if (selectedWarehouse != null) {
                    List<Worker> workers = workerService.getWorkerByWarehouseId(selectedWarehouse.getId());
                    DefaultComboBoxModel<Worker> model = new DefaultComboBoxModel<>();
                    model.addAll(workers);
                    workerCombo.setModel(model);
                    workerPanel.setVisible(true);
                }
            }
        });

        summaryPanel.add(workshopsPanel);
        summaryPanel.add(workerPanel);

    }

    private void setValidationButton(JPanel summaryPanel) {
        JButton validateButton = new JButton("Zaloguj");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Warehouse selectedWarehouse = (Warehouse) workshopCombo.getSelectedItem();
                Worker selectedWorker = (Worker) workerCombo.getSelectedItem();

                if (selectedWarehouse == null || selectedWorker == null) {
                    JOptionPane.showMessageDialog(null, "Brak uzupełnionych pól", "Błąd", JOptionPane.ERROR_MESSAGE);
                } else {
                    mainForm.setWorker(selectedWorker);
                    mainForm.setWarehouse(selectedWarehouse);
                    mainForm.changePanel(mainForm.createMainPanel());
                }
            }
        });
        summaryPanel.add(validateButton);
    }

}
