package priv.stud.database.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import priv.stud.database.utlis.DatabaseSession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractRepository<T, ID> implements ICrudRepository<T, ID> {
    protected final Session session = DatabaseSession.getSession();

    protected Class<T> clazz;

    protected AbstractRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T findById(ID id) {
        T entity = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            entity = session.get(clazz, (Long) id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return entity;
    }

    @Override
    public List<T> findListByField(String fieldName, String value){
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(fieldName), value));
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T findByFieldName(String fieldName, String value){
        return findListByField(fieldName, value).get(0);
    }

    @Override
    public T save(T saveObject) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(saveObject);
            transaction.commit();
            return saveObject;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            return null;
        }
    }

    @Override
    public boolean deleteById(ID id) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            T entity = session.get(clazz, (Long) id);
            if(entity != null){
                session.delete(entity);
                transaction.commit();
                return true;
            }
        } catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
        return false;
    }

    public boolean deleteByFieldName(String fieldName, String name){

        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            T entity = findByFieldName(fieldName, name);
            if(entity != null){
                session.delete(entity);
                transaction.commit();
                return true;
            }
        } catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(T entity) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        }
        return false;
    }

    @Override
    public boolean existsById(ID id) {
        boolean exists = false;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            exists = session.get(clazz, (Long) id) != null;
            transaction.commit();
        } catch(Exception e){
            if(transaction != null)
                transaction.rollback();
        }
        return exists;
    }
}
