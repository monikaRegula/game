package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.ArrayList;

/**
 * Class RocketLanding
 * @author Monika Reguła Katarzyna Jurkowska
 * @version 1.0
 */
public class RocketLanding implements Runnable,Observable {

    /**
     * Represents of th Thread
     */
    private Thread worker;
    /**
     * Represents state of Thread
     */
    protected volatile boolean isRunning = false;
    /**
     * Represents value of interval
     */
    private int interval=200;
    /**
     * Represents list of Observers
     */
    private volatile ArrayList<Observer> rocketUpdate = new ArrayList<>();
    /**
     * Represents object of Integration class with integration step = 0.01
     */
    private Integrator integrator = new Integrator(0.01);
    /**
     * Represents object of Rocket class
     */
    private Rocket rocket = new Rocket(2730.14,-150.0,50000.0,636.0,-16.5);

    /**
     * This method makes everything. It means it integrates. Add all values to concrete places == updates
     */
    public void everything(){
        //całkowanie
        integrator.integrate(rocket);
        //wyświetlanie wartości w konsoli
        integrator.show();
        //zmiana licznika integratora
        integrator.setN(integrator.getN()+1);
    }

    /**
     * This method when Thread is started then methods:everything() and updateObserver() are
     *       started. Then Thread goes to sleep for interval period time. For height <0 thread stops. It means rocket has landed or crushed.
     */
    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                //warunek lądowania rakiety
                if(integrator.gethV()<0)
                    //zatrzymanie
                    stop();
                else{
                    //metoda całkująca
                everything();
                //aktualizacja obserwatorów
                updateObserver(integrator);
                //uśpienie programu przez interwał czasowy
                Thread.sleep(interval);}
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Something went wrong");
            }
        }
    }

    /**
     * Method creates and starts Thread
     */
    public void start() {
        worker= new Thread(this, " Clock thread");
        worker.start();
    }
    /**
     * Method stops Thread
     */
    public void stop() {
        isRunning = false;
    }
    /**
     * Method stops Thread
     */
    public void interrupt() {//metoda do przycisku PAUSE
        isRunning = false;
        worker.interrupt();
    }

    /**
     * Method adds an Observer to ObserverList if list does not contain an Observer
     * @param observer Observer
     */
    @Override
    public void addObserver(Observer observer) {
        //metoda dodaje obserwatora do listy tylko jesli jeszcze go na niej nie ma
        if (!rocketUpdate.contains(observer))
            rocketUpdate.add(observer);
    }
    /**Method removes an Observer form ObserverList
     * @param observer Observer
     */
    @Override
    public void removeObserver(Observer observer) {
        //metoda usuwa obserwatora jeśli istnieje on na liście
        if (rocketUpdate.contains(observer)) {
            rocketUpdate.remove(observer);
        } else {//inaczej wyrzuca wyjątek
            throw new IllegalArgumentException("No such observer");
        }
    }

    /**
     * Method updates Observer with the current weather's parameters
     * @param integrator integrator
     */
    @Override
    public void updateObserver(Integrator integrator) {
        Platform.runLater(() -> {
            //dla obserwatorów uaktualnia się pole rocketUpdates po każdym ponownym odpalaniu się programu
            for (Observer observers : rocketUpdate) {
                observers.update(integrator);
            }
        });
    }
}
