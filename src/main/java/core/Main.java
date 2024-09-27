package core;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ragdoll using FXML.
 */
public class Main extends Application {

    private Ragdoll ragdoll = new Ragdoll();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "FXMLLoader failed to load /view/MainWindow.fxml";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRagdoll(ragdoll);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
