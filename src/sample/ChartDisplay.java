package sample;

import javafx.application.Platform;
import javafx.scene.chart.Axis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * Class ChartDisplay
 * @author Monika Reguła Katarzyna Jurkowska
 * @version 1.0
 */
public class ChartDisplay implements Observer,Display {

    /**
     * Represents chart
     */
    private ScatterChart<Number, Number> chart;
    /**
     * Represents chart series
     */
    private XYChart.Series<Number, Number> series = new XYChart.Series<>();
    /**
     * Represents y Axis
     */
    private Axis yAxis;
    /**
     * Represents x Axis
     */
    private Axis xAxis;

    /**
     * Makes an object with parameters:
     * @param chart chart
     * @param yAxis yAxis
     * @param xAxis xAxis
     */
    public ChartDisplay(ScatterChart<Number, Number> chart, Axis yAxis, Axis xAxis) {
        this.chart = chart;
        this.yAxis = yAxis;
        this.xAxis = xAxis;

    }


    /**
     * Method is overrided from Display inteface. It makes chart in JavaFX
     */
    @Override
    public void display() {
        chart.setAnimated(false);
        //usuwam dane z wykresu
        chart.getData().removeAll(chart.getData());
        //dodaje tytuł serii danych
        series.setName("Przestrzeń fazowa");
        //do wykresu dodaję serię danych
        chart.getData().addAll(series);

    }

    /**
     * Metod isoverrided form Observer interface. It adds new values to chart series
     * @param integrator integrator
     */
    @Override
    public void update(Integrator integrator) {
        Platform.runLater(() -> {
            //przypisuje pole klasy integrator do serii danych wykresow
            series.getData().add(new XYChart.Data<>(integrator.gethV(),integrator.getvV()));
        });
        display();
    }
}
