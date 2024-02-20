package gui;

import java.io.IOException;

import andelu.Andelu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Andelu bot using FXML.
 */
public class MainGui extends Application {


    private Andelu andelu;

    /**
     * Starts the GUI interface.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        andelu = new Andelu("duke");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainGui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Andelu Task Bot");
            fxmlLoader.<MainWindow>getController().setAndelu("duke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
