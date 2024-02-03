package view;

import java.io.IOException;

import config.Config;
import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * The main class of the application.
 * This class is responsible for starting the gui application.
 */
public class App extends Application {

    private Duke duke = new Duke();
    @Override
    public void start(Stage stage) {

        try {
            stage.setTitle(Config.getConfig().appName);
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
