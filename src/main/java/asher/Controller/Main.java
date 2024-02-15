package asher.Controller;

import asher.Asher;
import asher.Commands.Storage;
import asher.Tasks.TaskList;
import asher.Ui.Ui;

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
    @Override
    public void start(Stage stage) {
        try {
            Ui ui = new Ui();
            TaskList taskList = new TaskList();
            Storage storage = new Storage();
            Asher asher = new Asher(ui, taskList, storage);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("AsherBot");
            fxmlLoader.<MainWindow>getController().setAsher(asher);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


