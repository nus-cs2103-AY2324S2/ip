package chatbot.gui;

import java.io.IOException;

import chatbot.Duke;
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
 * Represents the main entry point for the Fatnom application.
 * Extends the JavaFX Application class and initialises the application's GUI.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Main extends Application {
    private Duke duke = new Duke();
    private TaskList tasks = duke.getTasklist();
    private Ui ui = duke.getUi();
    private Parser parser = duke.getParser();

    /**
     * Constructs a new Main instance.
     *
     * @throws DukeException For initialisation errors.
     */
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
