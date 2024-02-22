package emis;

import emisCommand.Command;
import emisExceptions.EmisException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main class of the EMIS application.
 * Duke handles the initialization of application components and the main application logic.
 */

public class Duke extends Application {

    /** The storage component for managing data. */
    private Storage storage;

    /** The task list component for managing tasks. */
    private TaskList tasklist;

    /** The user interface cokponent for interacting with users. */
    private Ui ui;

    /**
     * Constructs a new Duke object with the specified file path.
     * Intializes the user interface, storage, and task list components.
     * If loading tasks from the file fails, initializes an empty task list.
     * 
     * @param filePath The file path for storing application data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.loadFromStorageFile());
        } catch (EmisException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    public Duke() {
        
    }

    /**
     * Runs the EMIS application.
     * Displays a welcome message and continuously prompts the user for input.
     * Executes user commands and handles exceptions until the user exits the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (EmisException e) {
                ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
        Ui.exit();
    }

    /**
     * The entry point of the EMIS application.
     * Initializes a Duke object with the default file path and runs the application.
     * 
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke("./data/emis.txt").run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}