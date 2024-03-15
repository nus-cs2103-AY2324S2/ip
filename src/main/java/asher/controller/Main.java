package asher.controller;

import asher.Asher;
import asher.commands.Storage;
import asher.tasks.TaskList;
import asher.ui.Ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represent the main class for the Asher GUI using FXML.
 */
public class Main extends Application {
    /**
     * Start the Asher GUI.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
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


