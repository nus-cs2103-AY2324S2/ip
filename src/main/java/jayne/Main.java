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

    private static final String TITLE_NAME = "Jayne Android";
    private static final String FXML_FILE = "/view/MainWindow.fxml";
    private Jayne jayne = new Jayne("./out/jayne.txt");

    public Main() throws JayneException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_FILE));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(TITLE_NAME);
            fxmlLoader.<MainWindow>getController().setJayne(jayne);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "IOException should not occur if resources are correctly configured";
        }
    }
}
