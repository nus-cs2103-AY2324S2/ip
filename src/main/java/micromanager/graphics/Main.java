package micromanager.graphics;

import java.io.IOException;

import micromanager.logic.Logic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Logic logic = new Logic();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("MicroManager");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLogic(logic);
            fxmlLoader.<MainWindow>getController().displayGreeting();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
