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

    private Duke duke = new Duke("C:/repos/cs2103t stuff/data", "duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Balom: Your TaskManger");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.greeting();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
