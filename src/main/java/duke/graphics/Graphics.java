package duke.graphics;

import java.io.IOException;

import duke.graphics.MyClass;
import duke.ui.UserInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Graphics extends Application {

    private UserInterface ui = new UserInterface();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Graphics.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUserInterface(ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
