package irwyn;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Irwyn chatbot using FXML.
 */
public class Main extends Application {

    private final Irwyn irwyn = new Irwyn();

    /**
     * Starts the GUI.
     *
     * @param stage Stage to be taken in for display on the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/UiWindow.fxml"));
            assert fxmlLoader != null : "Need FXML loader";
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "Need Anchor Pane";
            Scene scene = new Scene(ap);
            assert scene != null : "Need Scene";
            stage.setScene(scene);
            assert stage != null : "Need Stage";
            stage.setTitle("Irwyn - chatbot!");
            fxmlLoader.<UiWindow>getController().setIrwyn(irwyn);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
