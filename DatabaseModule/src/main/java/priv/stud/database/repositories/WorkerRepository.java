package priv.stud.database.repositories;

import org.hibernate.Session;
import priv.stud.database.entities.warehouse.Worker;
import priv.stud.database.utlis.DatabaseSession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class WorkerRepository extends AbstractRepository<Worker, Long> {
    public  WorkerRepository() {
        super(Worker.class);
    }

    public List<Worker> findWorkersByWarehouseId(Long id){
        Session session = DatabaseSession.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Worker> criteriaQuery = criteriaBuilder.createQuery(Worker.class);
        Root<Worker> root = criteriaQuery.from(Worker.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("warehouse"), id));
        List<Worker> resultList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return resultList;
    }
}
