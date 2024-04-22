package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Path path = Paths.get("");
    private String pathString = path.toAbsolutePath().toString() + "/data";
    private Duke duke = new Duke(pathString, "duke.txt");

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
