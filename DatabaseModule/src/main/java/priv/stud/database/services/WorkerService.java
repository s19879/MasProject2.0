package priv.stud.database.services;

import priv.stud.database.entities.Address;
import priv.stud.database.entities.warehouse.*;

import java.sql.Date;
import java.util.List;

/**
 * Serwis do akcji na pracownikach
 */
public interface WorkerService {

    /**
     * Pobieranie pracownika po id magazynu
     *
     * @param id id pracownika
     * @return lista pracowników
     */
    List<Worker> getWorkerByWarehouseId(long id);

    /**
     * Zmiana pracownika magazynu z magazyniera na kierownika magazynu.
     * Jeśli przekazany pracownik jest już kierownikiem, nie jest wykonywana żadna akcja
     *
     * @param worker pracownik
     * @param seniority staż
     * @return pracownik
     */
    WarehouseWorker changeWarehouseWorkerToManager(WarehouseWorker worker, int seniority);

    /**
     *  Zmiana pracownika magazynu z kierownika magazynu na magazyniera.
     *  Jeśli przekazany pracownik jest już magazynierem, nie jest wykonywana żadna akcja
     *
     * @param worker pracownik
     * @param specialization specjalizacja magazyniera
     * @return pracownik
     */
    WarehouseWorker changeWarehouseWorkerToWarehouseman(WarehouseWorker worker, String specialization);

    /**
     * Sprawdzanie czy pracownik magazynu jest magazynierem
     *
     * @param warehouseWorker pracownik
     * @return wartość logiczna wskazująca czy pracownik magazynu jest magazynierem
     */
    boolean isWarehouseWorkerWarehouseman(WarehouseWorker warehouseWorker);

    /**
     * Sprawdzanie czy pracownik magazynu jest kierownikiem
     *
     * @param warehouseWorker pracownik
     * @return wartość logiczna wskazująca czy pracownik magazynu jest kierownikiem
     */
    boolean isWarehouseWorkerManager(WarehouseWorker warehouseWorker);

    /**
     * Pobieranie roli pracownika.
     *
     * @param worker pracownik
     * @return Zwracana jest wartość Stringowa. Nie chciałem tworzyć na potrzeby projektu dodatkowych enumów,
     * ponieważ i tak ich jest za dużo
     */
    String getWorkerRole(Worker worker);

    /**
     * Budowniczy do dodawania pracownika.
     * Z uwagi na złożoną strukturę dziedziczenia, aby ograniczyć ilość argumentów przekazywanych do konstrukt
     *
     * @param personalWorkerData dane personalne pracowniak
     * @param address adres
     * @return po wykonaniu build() zwracany jest obiekt pracownika
     *
     */
    WorkerBuilder builder(PersonalWorkerData personalWorkerData, Address address);

    /**
     * Budowniczy pod budowę pracownika
     * @see #asAccountant(String)
     * @see #asWarehouseWorker()
     * @see #build()
     */
    interface WorkerBuilder {
        /**
         * Wskazuję na chęć zbudowania księgowego. Po wykonaniu build() zostaje dodany
         * do bazy
         *
         * @param education wykształcenie
         *
         */
        WorkerBuilder asAccountant(String education);

        /**
         * Wskazuje na chęć zbudowania pracownika magazynu.
         * Wymaga użycia metod withManager() lub withWarehouseman() w celu uściślenia roli pracownika magazynu.
         * @see #withManager(int)
         * @see #withWarehouseman(String)
         */
        WorkerBuilder asWarehouseWorker();

        /**
         * Uściślenie po wywołaniu asWarehouseWorker (pracownika magazynu), że jest kierownikiem
         * @param seniority - staż
         * @see #asWarehouseWorker()
         */
        WorkerBuilder withManager(int seniority);

        /**
         * Uściślenie po wywołaniu asWarehouseWorker (pracownika magazynu), że jest magazynierem
         * @param specialization  specjalizacja
         * @see #asWarehouseWorker()
         */
        WorkerBuilder withWarehouseman(String specialization);

        /**
         * Wykonanie operacji dodawania pracownika w strukturze dziedziczenia
         * @return dodany pracownik
         * @see #builder(PersonalWorkerData, Address)
         */
        Worker build();
    }
}
