package priv.stud.database.services;

import lombok.NonNull;
import priv.stud.database.entities.orders.Order;
import priv.stud.database.entities.stores.Store;

import java.util.List;

/**
 * Serwis z akcjami dla sklepu
 *
 * @@author Przemysław Mizgała
 */
public interface StoreService {

    /**
     * Dodawanie sklepu firmowego
     *
     * @param name  nazwa
     * @param mail  mail
     * @param city  miasto
     * @param street  ulica
     * @param houseNumber  numer domu
     * @param zipCode  kod pocztowy
     * @param nameOfManager  imię i nazwisko kierownika sklepu
     * @param employeesNumber  liczba pracowników
     * @return zwraca obiekt dodanego sklepu
     */
    Store addCompanyStore( @NonNull String name, @NonNull String mail, @NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode, @NonNull String nameOfManager, int employeesNumber);

    /**
     * Dodanie sklepu zewnętrznego
     *
     * @param name - nazwa
     * @param mail - mail
     * @param city - miasto
     * @param street - ulica
     * @param houseNumber - numer domu
     * @param zipCode - kod pocztowy
     * @param loyality - współczynnik lojalności
     * @return zwraca obiekt dodanego sklepu
     */
    Store addExternalStore(@NonNull String name, @NonNull String mail, @NonNull String city, @NonNull String street, @NonNull String houseNumber, @NonNull String zipCode, double loyality);

    /**
     * Dodanie typu sklepu, który jest sklepem firmowym (overlapping)
     *
     * @param store - sklep
     * @param loyality - współczynnik lojalności
     * @return boolean z informacją czy dodanie się udało
     */
    boolean addExternalStoreToStore(Store store, double loyality);

    /**
     *  Dodanie typu sklepu, który jest sklepem firmowym (overlapping)
     *
     * @param store - sklep
     * @param nameOfManager - nazwa kierownika
     * @param employeesNumber - liczba pracowników
     * @return boolean z informacją czy dodanie się udało
     */
    boolean addCompanyStoreToStore(Store store, @NonNull String nameOfManager, int employeesNumber);
    void sendDailyEaringsAmount(Store store);
    boolean addOrderQualif(Store store, Order order);
    Order findOrderQualif(Store store, int id);

    /**
     * Pobiera wszystkie sklepy
     *
     * @return lista wszystkich sklepów
     */
    List<Store> findAllStores();

    Store findStoreById(Long id);

    List<String> getStoreTypes(Store store);

}
