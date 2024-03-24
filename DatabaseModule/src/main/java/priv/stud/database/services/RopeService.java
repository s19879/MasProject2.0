package priv.stud.database.services;

import priv.stud.database.entities.ropes.*;
import priv.stud.database.repositories.RopeRepository;

public class RopeService {
    private final RopeRepository ropeRepository;
    public RopeService(){
        ropeRepository = new RopeRepository();
    }

    public CommonRope createNewCommonRope(String name, int elongation, double diameeter, BasicRopeInfo basicRopeInfo){
        CommonRope rope = new CommonRope(name, elongation,  diameeter, true ,basicRopeInfo, "SINGLE");
        return (CommonRope) ropeRepository.save(rope);

    }

    public  TwinRope createNewTwinRope(String name, int elongation, double diameeter, BasicRopeInfo basicRopeInfo){
        TwinRope rope = new TwinRope(name, elongation,diameeter, true, basicRopeInfo, true);
        return (TwinRope) ropeRepository.save(rope);
    }
}
