package priv.stud.database.services;

import priv.stud.database.entities.ropes.BasicRopeInfo;
import priv.stud.database.entities.ropes.CommonRope;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.ropes.TwinRope;
import priv.stud.database.repositories.RopeRepository;

import java.util.List;

public interface RopeService {

    CommonRope createNewCommonRope(String name, int elongation, double diameeter, BasicRopeInfo basicRopeInfo);
    TwinRope createNewTwinRope(String name, int elongation, double diameeter, BasicRopeInfo basicRopeInfo);
    boolean deleteRope(Rope rope);
    Rope getRopeByName(String name);
    Rope getRopeById(int id);
    static List<Rope> getAllRopes(){
        RopeRepository repository = new RopeRepository();
        return repository.findAll();
    }
}
