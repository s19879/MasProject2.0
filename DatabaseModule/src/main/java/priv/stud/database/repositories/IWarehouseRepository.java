package priv.stud.database.repositories;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseLine;

import java.util.List;

public interface IWarehouseRepository extends ICrudRepository<Warehouse, Long> {
    List<WarehouseLine> getAllRopesInWorkshop();
    WarehouseLine getRopeInWorkshop(Rope rope);
    List<Order> getAllOrdersInMonth(int month, int year);
}
