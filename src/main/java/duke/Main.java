package duke;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import duke.Toothless;
import duke.frontend.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Toothless toothless = new Toothless();

    @Override
    public void start(Stage stage) {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(toothless);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
