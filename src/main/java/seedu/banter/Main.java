package seedu.banter;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.banter.ui.MainWindow;


/**
 * A GUI for Banter using FXML.
 */
public class Main extends Application {

    private final Banter banter = new Banter();

    /**
     * Starts the application.
     * @param stage The stage to start the application on.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css"))
                    .toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Banter");
            fxmlLoader.<MainWindow>getController().setBanter(banter);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
