import java.io.IOException;

import haro.Haro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final String dataPath = "data/saveList.txt";
    private final String directory = "data/";
    private Haro haro = new Haro(dataPath, directory);
    private Image haroIcon = new Image("/images/ExiaIcon.png");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Haro");
            stage.getIcons().add(haroIcon);
            fxmlLoader.<MainWindow>getController().setHaro(haro);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
