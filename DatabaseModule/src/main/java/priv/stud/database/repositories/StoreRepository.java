package priv.stud.database.repositories;

import priv.stud.database.entities.stores.Store;

public class StoreRepository extends AbstractRepository<Store, Long>{

    public StoreRepository(){
        super(Store.class);
    }
}
