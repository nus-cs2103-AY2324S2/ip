package jayne;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Jayne using FXML.
 */
public class Main extends Application {

    private Jayne jayne = new Jayne("./out/jayne.txt");

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
            assert false : "IOException should not occur if resources are correctly configured";
        }
    }
}
