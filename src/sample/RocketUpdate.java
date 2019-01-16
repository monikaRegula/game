package sample;

/**
 * Class RocketUpdate
 * @author Monika Reguła Katarzyna Jurkowska
 * @version 1.0
 */
import javafx.collections.ObservableList;

public class RocketUpdate implements Observer {
    /**
     * List of observers that are Integrator's atributes
     */
    private ObservableList<Integrator> data;

    /**
     * Makes object of the RocketUpdate class with parameter data
     * @param data data
     */
    public RocketUpdate(ObservableList<Integrator> data)
    { this.data = data; }

    /**
     * Method is overrided from interface Observer. It makes parameters of integrator updated
     * @param integrator integrator
     */
    @Override
    public void update(Integrator integrator) {
        data.add(integrator);

    }

}
