package sample;

/**
 * Observable interface
 * @author Monika Reguła Katarzyna Jurkowska
 * @version 1.0
 */
public interface Observable {

    /**
     * Method adds an observer to list
     * @param observer Observer
     */
    void addObserver(Observer observer);
    /**
     * Method removes an observer from list
     * @param observer Observer
     */
    void removeObserver(Observer observer);
    /**
     * Method sends data to an observer form the list
     * @param integrator
     */
    void updateObserver(Integrator integrator);
}
