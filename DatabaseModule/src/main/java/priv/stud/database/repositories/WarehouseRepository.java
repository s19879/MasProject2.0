package priv.stud.database.repositories;

import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

import java.util.List;

public class WarehouseRepository extends AbstractRepository<Warehouse, Long>
    implements IWarehouseRepository{
    public WarehouseRepository() {
        super(Warehouse.class);
    }

    @Override
    public List<WarehouseRope> getAllRopesInWorkshop() {
        return null;
    }

    @Override
    public WarehouseRope getRopeInWorkshop(Rope rope) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersInMonth(int month, int year) {
        return null;
    }
}
