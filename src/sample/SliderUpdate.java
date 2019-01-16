package sample;


import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.util.ArrayList;
/**
 * Class SliderUpdate
 * @author Monika Reguła
 * @version 1.0
 */
public class SliderUpdate implements Observer,Display {

    /**
     * Represents TextFiled in JavaFX
     */
    private TextField textField;
    /**
     * Represents arraylist of fuel usage values
     */
    private ArrayList<Double> uValue=new ArrayList<>();
    /**
     * Represents slider in JavaFX
     */
    private Slider slider;


    /**
     * Method gets values of fuel usage
     * @return
     */
   // public ArrayList<Double> getuValue() { return uValue; }

    /**
     * Makes an object of the SliderUpdate class with parameters:
     * @param textField textfield
     * @param slider slider
     */
    public SliderUpdate(TextField textField, Slider slider) {
        this.textField = textField;
        this.slider = slider;
    }


    /**
     * Method displays on Textfield in JavaFX value of Slider
     */
    @Override
    public void display() {
        Platform.runLater(() -> {
            textField.setText(String.valueOf(slider.getValue()));
        });
    }

    /**
     * Method updates data from slider that will be shown in Textfield
     * @param integrator integrator
     */
    @Override
    public void update(Integrator integrator) {
        if (integrator.getmV() != 0){
            uValue.add(slider.getValue());
            integrator.uValues.add(integrator.getN(),slider.getValue());
        }
        display();
    }

}