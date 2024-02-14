package ben;

import ben.commands.Command;
import ben.exceptions.BenException;
import ben.parser.Parser;
import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * The main class for the Ben task management application.
 */
public class Main extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Main() {

    }

    /**
     * Constructs the Main object with the specified file path for storage.
     *
     * @param filePath The file path for storage.
     */
    public Main(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BenException | FileNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Ben task management application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks.formatSave());
            } catch (BenException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * The main method to start the Ben task management application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Main("data/tasks.txt").run();
    }
}
