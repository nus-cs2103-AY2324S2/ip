package yapchit.yapchitui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Yapchit using FXML.
 */
public class Main extends Application {

    private Yapchit yapchit = new Yapchit();

    /**
     * Starts the application by adding parts to the stage and then showing the stage.
     *
     * @param stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Yapchit");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYapchit(yapchit);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
