package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author KohGuanZeh
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Duke duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Stage mainWindowStage = fxmlLoader.load();
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setDuke(duke);
            mainWindowStage.show();
            mainWindow.onStartUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
