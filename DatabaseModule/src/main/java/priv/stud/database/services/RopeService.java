package priv.stud.database.services;

import priv.stud.database.entities.ropes.*;
import priv.stud.database.repositories.RopeRepository;

import java.util.List;

/**
 * Serwis wykonujący akcje na linach
 *
 * @@author Przemysław Mizgała
 */
public interface RopeService {

    /**
     * Dodawanie nowej liny
     * @param name - nazwa liny
     * @param elongation - długość
     * @param diameter - średnica
     * @param ropeType - lina dynamiczna czy statyczna
     * @param basicRopeInfo - informacje podstawowe o linie
     * @param ropeKind - lina połówkowa czy pojedyncza
     * @return zwraca obiekt liny
     */
    Rope createNewCommonRope(String name, int elongation, double diameter, RopeType ropeType, BasicRopeInfo basicRopeInfo, String ropeKind);

    /**
     * Dodawanie nowej liny podwójnej
     *
     * @param name - nazwa liny
     * @param elongation - długość
     * @param diameter - średnica
     * @param ropeType - dynamicnza czy statyczna
     * @param basicRopeInfo - podstawowe informacje
     * @param isDesignedForIceClimbing - czy stworozna do wspinaczki lodowej
     * @return zwraca obiekt liny
     */
    Rope createNewTwinRope(String name, int elongation, double diameter, RopeType ropeType, BasicRopeInfo basicRopeInfo, boolean isDesignedForIceClimbing);

    boolean deleteRope(Rope rope);
    Rope getRopeByName(String name);
    Rope getRopeById(int id);
    List<Rope> getAllRopes();
}
