package priv.stud.database.repositories;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

import java.util.List;

public interface IWarehouseRepository extends ICrudRepository<Warehouse, Long> {
    List<WarehouseRope> getAllRopesInWorkshop();
    WarehouseRope getRopeInWorkshop(Rope rope);
    List<Order> getAllOrdersInMonth(int month, int year);
}
