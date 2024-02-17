package duke.application;

import duke.io.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import duke.io.Storage;
import duke.io.Ui;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    public static String getResponse(String input, TaskList taskList, Parser parser) {
        assert input != null : "Input should not be null";
        assert taskList != null : "TaskList should not be null";
        assert parser != null : "Parser should not be null";

        if ("bye".equalsIgnoreCase(input)) {
            Storage.saveTasks(taskList.getTaskList());
            return "Goodbye! Thank you for using RATZCHAT!";
        } else {
            String dukeResponse = parser.handleCommand(input, taskList);
            return dukeResponse;
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            Ui ui = new Ui();
            ui.showWelcomeMessage();

            TaskList taskList = new TaskList();
            Parser parser = new Parser();
            Storage.loadFile(taskList.getTaskList());
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            controller.setDuke(duke, taskList, parser);
            controller.showWelcomeMessage();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
