package sample;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class Controller
 * @Monika Reguła Katarzyna Jurkowska
 * @version 1.0
 */
public class Controller implements Initializable {
    /**
     * Represents Object of class Integrator
     */
    private  Integrator integrator = new Integrator(0.01);
    /**
     * Represents Observable List of Observers :
     */
    private final ObservableList<Integrator> data = FXCollections.observableArrayList(
            integrator );
    /**
     * Represents ImageView of buzzAstralFly.jpg in JavaFX
     */
    @FXML
    private ImageView buzzAstralFly;
    /**
     * Represents ImageView of buzzAstralStand.jpg in JavaFX
     */
    @FXML
    private ImageView buzzAstralStand;
    /**
     * Represents chart in JavaFX
     */
    @FXML
    private ScatterChart<Number, Number> chart;
    /**
     * Represents of x Axis
     */
    @FXML
    private NumberAxis xAxis;
    /**
     * Represents of y Axis
     */
    @FXML
    private NumberAxis yAxis;
    /**
     * Represents button Start
     */
    @FXML
    private Button btnStart;
    /**
     * Represents slider
     */
    @FXML
    private Slider slider;
    /**
     * Represents TextField with height in JavaFX
     */
    @FXML
    private TextField txtH;
    /**
     * Represents TextField with velocity in JavaFX
     */
    @FXML
    private TextField txtV;

    @FXML
    private TextField txtT;
    /**
     * Represents TextField with mass in JavaFX
     */
    @FXML
    private TextField txtM;
    /**
     * Represents second pane
     */
    @FXML
    private Pane pane2;
    /**
     * Represents Textfield with current fuel usage form slider in JavaFX
     */
    @FXML
    private TextField txtFuel;
    /**
     * Represents the controller
     */
    private static Controller controller;


    /**
     * Method is activated when button Start is pressed.
     * Method makes chart of velocity(height), displays value form slider and moves image of BuzzAstral
     * @param event event
     */
    @FXML
    void btnStartPressed(ActionEvent event) {
        //czyszcze dane z list tablicowych
        integrator.clearData();
        //Ustawiam widzialność obu Buzzów
        buzzAstralFly.setVisible(true);
        buzzAstralStand.setVisible(false);
        //Ustawiam Buzza-rakietę na miejscu startowym
        buzzAstralFly.setY(47);
        // wartość z suwaka
        int sliderValue  = (int) slider.getValue();
        System.out.println(sliderValue+"SLIDER ");
        txtFuel.setText(String.valueOf(sliderValue));
        //Wstawiam plik mp3
         AudioClip audioClip = new AudioClip(getClass().getResource("/song.mp3").toString()) ;
         audioClip.play();

        //czyszcze wykres
        chart.getData().removeAll(chart.getData());

        //Tworzę symulator lądowania rakiety:
        RocketLanding game = new RocketLanding();
        //wywołuję metodę start
        game.start();
        //czyszczę dane na liście obserwatorów klasy Integrator
        data.clear();

        //Tworzę obiekt klasy RocketUpdate
        RocketUpdate  rocketUpdate = new RocketUpdate(data);
        //dodaję rocketUpdate do listy obserwatorów
        game.addObserver(rocketUpdate);

        //Tworzę obiekt klasy TexFieldDisplay
        TextFieldDisplay txtShow= new TextFieldDisplay(txtH,txtV,txtM);
        //robię txtShow obserwatorem dodając go do listy obserwatorów
        game.addObserver(txtShow);

        //Tworzę obiekt klasy ChartDataDisplay
        ChartDisplay chartt = new ChartDisplay(chart,yAxis,xAxis);
        //robię chartt obserwatorem dodając go do listy obserwatorów
        game.addObserver(chartt);

        //Tworzę obiekt klasy SliderUpdate
        SliderUpdate sliderUpdate = new SliderUpdate(txtFuel,slider);
        //robię sliderUdate obserwatorem dodając go do listy obserwatorów
        game.addObserver(sliderUpdate);

    }

    /**
     * Method who initialize transfer an ImageView object to Image object
     * @param location location
     * @param resourses resourses
     */
    @Override
    public void initialize(URL location, ResourceBundle resourses){
        File file1 = new File("src/buzzFly.png");
        Image image1 = new Image(file1.toURI().toString());
        File file2 = new File("src/buzzStand.png");
        Image image2 = new Image(file2.toURI().toString());
        controller = this;
    }


    /**
     * Method declare Controller's getter
     * @return controller
     */
    public static Controller getController() {
        return controller;
    }

    /**
     * Method moves the Buzz Astral by taking real value of height from Integrator Class
     * @param h h
     */
    @FXML
    public void rocketViewLanding(double h){

        //początkowa wartość wysokości Buzza
        double buzzFlyH = 45;
        //końcowa wartość wysokości Buzza
        double buzzStandH = 540;
        //droga, którą Buzz będzie musiał pokonać
        double pixelH = buzzStandH-buzzFlyH;
        //wysokość rzeczywista
        double realMaxH =50000;
        //aktualizowana wysokość (całkowana)
        double realH = h;
        //obliczenia sprawdzają, na jakiej wysokości powinien się znaleźć Buzz
        double pixelFallH = (pixelH*realH)/realMaxH;
        double buzzUpdateH=buzzStandH-pixelFallH;

        //przekazanie obliczonej wysokości i ustawienie na niej Buzza
        Platform.runLater(() -> {
            buzzAstralFly.setY(buzzUpdateH);
        });

        //warunek: w przypadku zakończenia lotu, obrazki Buzzów się podmienią
        if (realH<0){
            buzzAstralFly.setVisible(false);
            buzzAstralStand.setVisible(true);
        }



    }



}
