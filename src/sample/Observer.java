package sample;
/**
 * Interface Observer
 * @author Monika Reguła Katarzyna Jurkowska
 * @version 1.0
 */
public interface Observer {
    /**
     * Method gets updated data from observable
     * @param integrator integrator
     */
    void update(Integrator integrator);
}
