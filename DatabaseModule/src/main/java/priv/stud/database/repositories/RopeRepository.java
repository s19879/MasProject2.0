package priv.stud.database.repositories;

import priv.stud.database.entities.ropes.Rope;

public class RopeRepository extends AbstractRepository<Rope, Long>{

    public RopeRepository() {
        super(Rope.class);
    }
}
