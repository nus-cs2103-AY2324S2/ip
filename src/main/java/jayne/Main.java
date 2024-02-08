package jayne;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Jayne jayne = new Jayne("./out/jayne.txt");
    @FXML
    private VBox dialogContainer;

    public Main() throws JayneException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Jayne Android");
            fxmlLoader.<MainWindow>getController().setJayne(jayne);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
