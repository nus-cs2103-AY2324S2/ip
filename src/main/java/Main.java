import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Taylor using FXML.
 */
public class Main extends Application {

    /**
     * Instantiates Taylor ChatBot class
     */
    private Taylor taylor = new Taylor();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Taylor");
            fxmlLoader.<MainWindow>getController().setTaylor(taylor);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
