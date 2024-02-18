package chatbot;

import java.io.IOException;

import chatbot.exception.DukeException;
import chatbot.parser.Parser;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private TaskList tasks = duke.getTasklist();
    private Ui ui = duke.getUi();
    private Parser parser = duke.getParser();

    public Main() throws DukeException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke, parser);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
