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
public class GraphicalUI extends Application {

    private final Logic logic = new Logic();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUI.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUserInterface(logic);
            stage.show();
            fxmlLoader.<MainWindow>getController().greet();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
