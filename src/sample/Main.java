package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *Class Main creates Stage and starts the application
 */
public class Main extends Application {

    /**
     * Method start sets Scene name and Scene size then shows scene
     * @param primaryStage Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lądowanie na Marsie");
        primaryStage.setScene(new Scene(root, 1100, 800));
        primaryStage.show();
    }

    /**
     * Launches the application
     * @param args initial arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
