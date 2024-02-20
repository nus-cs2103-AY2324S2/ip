package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @@author KohGuanZeh-reused
 * Source: https://se-education.org/guides/tutorials/javaFxPart3.html
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Duke duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Stage mainWindowStage = fxmlLoader.load();
            MainWindow mainWindow = fxmlLoader.getController();
            assert(mainWindow != null);
            mainWindow.setDuke(duke);
            mainWindowStage.show();
            mainWindow.onStartUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
