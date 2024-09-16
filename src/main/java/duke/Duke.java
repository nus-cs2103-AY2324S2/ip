package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Duke class is the main class for the application.
 */
public class Duke extends Application {
    private Parser parser = new Parser();
    private TaskList tasks = new TaskList();
    private Storage storage = new Storage();

    /**
     * Starts the application.
     * @param stage The stage to start the application on.
     */
    @Override
    public void start(Stage stage) {
        Commands.registerCommands();
        try {
            tasks.loadFromSavedData(storage.loadFromFile(), parser);
        } catch (DukeException e) {
            storage.deleteFile();
            //ui.printCorruptedFileMessage(e);
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(Constants.WINDOW_TITLE);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the response from the user input.
     * @param input The user input.
     * @return The program's response.
     */
    public String getResponse(String input) {
        Command command;
        try {
            command = parser.parse(input);
            String result = command.execute(tasks);
            storage.saveToFile(tasks.getSaveData());
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
