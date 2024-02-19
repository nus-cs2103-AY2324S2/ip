package kai;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Kai kai = new Kai();
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("KAI");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(kai);
            stage.show();
            MainWindow controller = fxmlLoader.getController();
            controller.setDuke(kai);

            // Display welcome message
            controller.showWelcomeMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
