package priv.stud.database.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import priv.stud.database.utlis.DatabaseSession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * Klasa generyczna, zawierająca najczęściej powtarzające się metody przy komunikacji z bazą
 *
 * @param <T> - parametr określający na jakiej klasie operujemy i jaki typ ma zwrócić, oraz przyjąć przy wykonywaniu metod
 * @param <ID> - typ id
 */
public abstract class AbstractRepository <T, ID> implements ICrudRepository<T, ID>{
    /**
     * należy koniecnzie przekazać i jest wykorzystywany głównie przy criteria builder
     */
    protected Class<T> clazz;

    protected AbstractRepository(Class<T> clazz) {
        this.clazz = clazz;
    }


    /**
     * Znajdź wsystkie wystąpienia
     *
     * @return Lista o określonym typie
     */
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

    /**
     * Wyszukanie po id
     *
     * @param id - id po którym wyszukujemy
     * @return zwrócony obiekt
     */
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

    /**
     * Wyszukiwanie wszystkich wystąpień po porównaniu określonej kolumny z wartością tekstową
     *
     * @param fieldName - nazwa kolumny
     * @param value - porównywana wartość
     * @return lista obiektów
     */
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

    /**
     * Wyszukiwanie pierwszego wystąpienia po porównaniu określonej kolumny z wartością tekstową
     *
     * @param fieldName - nazwa kolumny
     * @param value - porównywana wartość
     * @return lista obiektów
     */
    @Override
    public T findByFieldName(String fieldName, String value){
        return findAllByField(fieldName, value).get(0);
    }

    /**
     * Zapis do bazy
     *
     * @param saveObject - zapisywany obiekt
     * @return zapisywany obiekt
     */
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

    /**
     * Usuń po id
     *
     * @param id - id rekordu, którego chcemy usunąć
     * @return boolean określający czy rekord został usunięty
     */
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

    /**
     * Usuwanie po wartości w określonej kolumnie
     *
     * @param fieldName - nazwa kolumny
     * @param name wartość po której chcmey usuąć
     * @return boolean czy rekordy usuniete
     */
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

    /**
     * Usunięcie określonego rekordu
     *
     * @param entity - obiekt który chcemy usunąć
     * @return - boolean czy rekord usunięty
     */
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

    /**
     * czy istnieje rekord o id
     *
     * @param id - id
     * @return boolean czy istnieje
     */
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

    /**
     * Czy istnieje rekord po nazwie
     *
     * @param fieldName - nazwa kolumny
     * @param name - wyszukiwana watość
     * @return boolean czy itnieje
     */
    @Override
    public boolean existByName(String fieldName, String name){
        return !findAllByField(fieldName, name).isEmpty();
    }


}
