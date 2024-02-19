package echon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// @@author benson1029-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications
/**
 * A GUI for Echon using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Echon");
            fxmlLoader.<MainWindow>getController().startEchon();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// @@author
