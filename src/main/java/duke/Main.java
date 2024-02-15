package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    //private Duke duke = new Duke();
    private Duke duke = new Duke("./data/jamie.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert Main.class.getResource("/view/MainWindow.fxml") != null : "FXML file should exist";
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane should not be null after loading FXML";
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
