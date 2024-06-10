package priv.stud.database.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import priv.stud.database.utlis.DatabaseSession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractRepository <T, ID> implements ICrudRepository<T, ID>{
    protected Class<T> clazz;

    protected AbstractRepository(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public List<T> findAll(){
        Session session = DatabaseSession.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        CriteriaQuery<T> all = criteriaQuery.select(root);
        List<T> resultList = session.createQuery(all).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public T findById(ID id) {
        Session session = DatabaseSession.openSession();
        T entity = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            entity = session.get(clazz, (Long) id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public List<T> findAllByField(String fieldName, String value){
        Session session = DatabaseSession.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(fieldName), value));
        List<T> resultList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public T findByFieldName(String fieldName, String value){
        return findAllByField(fieldName, value).get(0);
    }

    @Override
    public T save(T saveObject) {
        Session session = DatabaseSession.openSession();
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
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteById(ID id) {
        Session session = DatabaseSession.openSession();
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
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteByFieldName(String fieldName, String name){
        Session session = DatabaseSession.openSession();
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
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(T entity) {
        Session session = DatabaseSession.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.remove(entity);
            session.flush();
            session.evict(entity);
            transaction.commit();

            return true;
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean existById(ID id) {
        Session session = DatabaseSession.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            boolean exists = session.get(clazz, (Long) id) != null;
            transaction.commit();
        } catch(Exception e){
            if(transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean existByName(String fieldName, String name){
        return !findAllByField(fieldName, name).isEmpty();
    }


}
