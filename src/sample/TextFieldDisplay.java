package sample;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Class TextFieldDisplay
 * @author Monika Reguła Katarzyna Jurkowska
 * @version 1.0
 */
public class TextFieldDisplay implements Observer,Display {

    /**
     * Represents TextFields in JavaFX of mass, height, velocity
     */
    private TextField h,v,m;
    /**
     * Represents arraylist with masses
     */
    private ArrayList<Double> mValue=new ArrayList<>();
    /**
     * Represents araylist with velocities
     */
    private ArrayList<Double> vValue=new ArrayList<>();
    /**
     * Represents arraylist with heights
     */
    private ArrayList<Double> hValue=new ArrayList<>();

    /**
     * Represents counter
     */
    private int n=0;

    /**
     * Method gets value of counter
     * @return n
     */
    public int getN() { return n; }

    /**
     * Makes an object with parameters:
     * @param h height
     * @param v velocity
     * @param m mass
     */
    public TextFieldDisplay(TextField h, TextField v, TextField m) {
        this.h = h;
        this.v = v;
        this.m = m;
    }

    /**
     * Method  is overrided from Display interface. It changes TextFields in JavaFX
     */
    @Override
    public void display() {
        Platform.runLater(() -> {
            //zmiana wartości wyświetlanych w textfieldach
            h.setText(String.valueOf(hValue.get(getN()-1)));
            v.setText(String.valueOf(-vValue.get(getN()-1)));
            m.setText(String.valueOf(mValue.get(getN()-1)));
        });
    }

    /**
     * Method is overrided from Observer interface.It updates data that will be shown in textfields.
     * @param integrator integrator
     */
    @Override
    public void update(Integrator integrator) {
        if (integrator.gethV() > 0){
            hValue.add(integrator.gethV());
            mValue.add(integrator.getmV());
            vValue.add(integrator.getvV());
            display();
            n++;
        }
        else{
            hValue.add(0.0);//dla wysokości mniejszej od zera koniec lądowania
            display();
            n++;
        }

    }


}
