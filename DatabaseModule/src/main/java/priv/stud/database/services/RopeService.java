package priv.stud.database.services;

import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.repositories.RopeRepository;

public class RopeService {
    private final RopeRepository ropeRepository;
    public RopeService(){
        ropeRepository = new RopeRepository();
    }

    public boolean createRope(){
        Rope rope = new Rope();
        return ropeRepository.save(rope);
    }
}
