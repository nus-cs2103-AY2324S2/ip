package simpli.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import simpli.core.Simpli;

/**
 * GUI for simpli application.
 */
public class Gui extends Application {
    private Simpli simpli;

    /** Starts running the GUI application.
     *
     * @param stage JavaFx stage.
     */
    @Override
    public void start(Stage stage) {
        simpli = new Simpli();
        simpli.start();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSimpli(simpli);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        simpli.stop();
    }
}
