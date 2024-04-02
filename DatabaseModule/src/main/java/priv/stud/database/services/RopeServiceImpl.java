package priv.stud.database.services;

import priv.stud.database.entities.ropes.*;
import priv.stud.database.repositories.RopeRepository;

public class RopeServiceImpl implements RopeService {
    private final RopeRepository ropeRepository;
    public RopeServiceImpl(){
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

    @Override
    public boolean deleteRope(Rope rope) {
        return false;
    }

    @Override
    public Rope getRopeByName(String name) {
        return ropeRepository.findByFieldName("name", name);
    }

    @Override
    public Rope getRopeById(int id) {
        return ropeRepository.findById((long) id);
    }
}
