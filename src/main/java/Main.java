import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Pew using FXML.
 */
public class Main extends Application {

    private Pew pew = new Pew("pew.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("PeWPeWPeW");
            fxmlLoader.<MainWindow>getController().setPew(pew);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}