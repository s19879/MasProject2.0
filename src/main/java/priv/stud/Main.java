package priv.stud;

import priv.stud.database.services.DummyService;
import priv.stud.database.services.RopeService;

public class Main {
    public static void main(String[] args) {
        DummyService dummy = new DummyService();
        System.out.println("Hello world!");
        RopeService rs = new RopeService();
        rs.createRope();
    }
}