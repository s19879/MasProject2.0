package priv.stud.database.repositories;

import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WarehouseRopeRepository extends AbstractRepository<WarehouseRope, Long> {
    public WarehouseRopeRepository() {
        super(WarehouseRope.class);
    }

    public WarehouseRope getWarehouseRope(Rope rope, Warehouse warehouse){
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<WarehouseRope> criteriaQuery = criteriaBuilder.createQuery(WarehouseRope.class);
        Root<WarehouseRope> root = criteriaQuery.from(WarehouseRope.class);
        Predicate conditionRope = criteriaBuilder.equal(root.get(Rope.class.getSimpleName().toLowerCase()), rope.getId()); //lower case bo tego wymaga builder
        Predicate conditionWarehouse = criteriaBuilder.equal(root.get(Warehouse.class.getSimpleName().toLowerCase()), warehouse.getId());
        Predicate conditions = criteriaBuilder.and(conditionRope, conditionWarehouse);
        criteriaQuery.select(root).where(conditions);
        return session.createQuery(criteriaQuery).getResultList().get(0);
    }
}
