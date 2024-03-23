package priv.stud;

import priv.stud.database.entities.ropes.BasicRopeInfo;
import priv.stud.database.entities.ropes.CommonRope;
import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.services.DummyService;
import priv.stud.database.services.RopeService;

public class Main {
    public static void main(String[] args) {
        DummyService dummy = new DummyService();
        System.out.println("Hello world!");
        RopeService rs = new RopeService();
        BasicRopeInfo bri = new BasicRopeInfo(499.99, 60, 1, 1, "ZYGZAK", 1, false);
        rs.createNewCommonRope("Dragon", 1, 0.1, bri);

    }
}