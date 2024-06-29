package priv.stud.database.services;

import priv.stud.database.entities.ropes.Rope;
import priv.stud.database.entities.warehouse.Warehouse;
import priv.stud.database.entities.warehouse.WarehouseRope;

/**
 * Serwis dla akcji dla lin w magazynie
 *
 * @@author Przemysław Mizgała
 */
public interface WarehouseRopeService {
    /**
     * Pobranie liny w magazynie wraz z ilością
     *
     * @param rope - lina której szukamy
     * @param warehouse - magazyn w którym szukamy
     * @return zwracany obiekt przetrzymujący info o linie w magazynie
     */
    WarehouseRope getWarehouseRope(Rope rope, Warehouse warehouse);

    /**
     * Zapis bądź update liny w magazynie
     *
     * @param warehouseRope - lina w magazynie którą chcemy zapisać/zaktualizować
     *
     */
    void saveWarehouseRope(WarehouseRope warehouseRope);

    /**
     * Zapis liny w magazynie
     * @param amount - ilość
     * @param rope - lina, którą chcemy dodać
     * @param warehouse - magazyn do którego chcemy dodać linę
     */
    void saveWarehouseRope(int amount, Rope rope, Warehouse warehouse);

    /**
     * Aktualizacja ilości liny
     *
     * @param rope - lina
     */
    void updateAmount(WarehouseRope rope);
}
