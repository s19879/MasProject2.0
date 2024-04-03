package priv.stud.database.services;

import priv.stud.database.entities.ropes.*;
import priv.stud.database.repositories.RopeRepository;

import java.util.List;

public interface RopeService {

    CommonRope createNewCommonRope(String name, int elongation, double diameter, RopeType ropeType, BasicRopeInfo basicRopeInfo);
    TwinRope createNewTwinRope(String name, int elongation, double diameter, RopeType ropeType, BasicRopeInfo basicRopeInfo);
    boolean deleteRope(Rope rope);
    Rope getRopeByName(String name);
    Rope getRopeById(int id);
    List<Rope> getAllRopes();
}
