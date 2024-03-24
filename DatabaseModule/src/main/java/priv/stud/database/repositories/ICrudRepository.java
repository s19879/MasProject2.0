package priv.stud.database.repositories;

public interface ICrudRepository<T, ID>{
    T findById(ID id);
    T save(T saveObject);
    boolean deleteById(ID id);
    boolean delete(T entity);
    boolean existsById(ID id);
}
