package priv.stud.database.repositories;

import java.util.List;

public interface ICrudRepository<T, ID>{
    T findById(ID id);
    List<T> findListByField(String fieldName, String value);
    T findByFieldName(String fieldName, String value);
    T save(T saveObject);
    boolean deleteById(ID id);
    boolean deleteByFieldName(String fieldName, String name);
    boolean delete(T entity);
    boolean existsById(ID id);
}
