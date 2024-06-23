package priv.stud.forms;

import lombok.NonNull;
import priv.stud.database.services.ServiceFactory;
import priv.stud.database.services.WarehouseService;
import priv.stud.database.services.WorkerService;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends CustomPanel{

    WarehouseService warehouseService;
    WorkerService workerService;
    protected LoginPanel(@NonNull MainForm mainForm) {
        super(mainForm);
        warehouseService = ServiceFactory.getWarehouseService();
        workerService = ServiceFactory.getWorkerService();

        setTitle("Panel logowania");

        JPanel summaryPannel = new JPanel();
        summaryPannel.setLayout(new GridLayout(2,1));
        summaryPannel.setPreferredSize(new Dimension(300, 400));
        setWarehouseList(summaryPannel);
        summaryPannel.add(createButtonPanel("text", null));

        setContentPanel(summaryPannel);

    }

    private void setWarehouseList(JPanel summaryPanel) {
        JPanel workshopsPanel = createNewPanel();
        JLabel storeLabel = new JLabel("Wybierz magazyn: ");
        workshopsPanel.add(storeLabel);

        JComboBox workshopCombo =  new JComboBox<>(warehouseService.findAllWarehouses().toArray());
        workshopCombo.setSelectedItem(null);
        workshopCombo.setSize(100,20 );
        workshopsPanel.add(workshopCombo);
        summaryPanel.add(workshopsPanel);


    }

}
