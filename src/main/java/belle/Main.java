package belle;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Belle using FXML.
 */
public class Main extends Application {

    private Belle belle = new Belle("src/main/data/belleList.txt");


    /**
     * Initiates application.
     *
     * @param stage Stage for application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Belle");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBelle(belle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
