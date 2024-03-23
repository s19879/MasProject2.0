package priv.stud.database.services;

import priv.stud.database.entities.ropes.BasicRopeInfo;
import priv.stud.database.entities.ropes.CommonRope;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.repositories.RopeRepository;

public class RopeService {
    private final RopeRepository ropeRepository;
    public RopeService(){
        ropeRepository = new RopeRepository();
    }

    public boolean createRope(){
        //Rope rope = new Rope();
        CommonRope rope = new CommonRope();
        rope.setName("Dragon");

        rope.setRopeKind("blabkala");
        rope.setBasicRopeInfo(new BasicRopeInfo());
        return ropeRepository.save(rope);

    }


}
