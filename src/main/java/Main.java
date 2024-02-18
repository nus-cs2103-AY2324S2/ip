import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Snom using FXML.
 */
public class Main extends Application {

    private Snom snom = new Snom();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Snom");
            fxmlLoader.<MainWindow>getController().setSnom(snom);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}