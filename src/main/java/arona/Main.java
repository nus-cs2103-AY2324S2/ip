package arona;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Arona using FXML.
 */
public class Main extends Application {

    private Arona arona;

    @Override
    public void start(Stage stage) {
        try {
            arona = new Arona();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setArona(arona);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.setTitle("Arona");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TaskException e) {
            e.getMessage();
        }
    }
}

