package duke.graphics;

import java.io.IOException;

import duke.logic.Logic;
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
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLogic(logic);
            fxmlLoader.<MainWindow>getController().displayGreeting();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
