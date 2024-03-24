package priv.stud.database.services;

import priv.stud.database.entities.ropes.BasicRopeInfo;
import priv.stud.database.entities.ropes.CommonRope;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.ropes.TwinRope;

public interface IRopeService {

    CommonRope createNewCommonRope(String name, int elongation, double diameeter, BasicRopeInfo basicRopeInfo);
    TwinRope createNewTwinRope(String name, int elongation, double diameeter, BasicRopeInfo basicRopeInfo);
    Rope getRopeByName(String name);
    Rope getRopeById(int id);
}
