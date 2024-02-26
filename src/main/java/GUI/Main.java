package GUI;
import java.io.File;
import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private String FILE_NAME = "duke.txt";
    private String FILE_PATH = System.getProperty("user.dir") + File.separator + "src//main/java/data"
            + File.separator + FILE_NAME;

    private Duke duke = new Duke();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Leo");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Duke duke = new Duke(FILE_PATH);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
