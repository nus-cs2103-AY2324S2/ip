package bob;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import bob.command.Command;
import bob.exception.BobException;

/**
 * Represents Bob itself. A <code>Bob</code> object corresponds to an instance of the program.
 */
public class Bob extends Application  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bob() {
        // ...
    }

    /**
     * Returns an instance of the program with its own storage, task list and UI.
     *
     * @param dataPath The file path of the storage.
     */
    public Bob(String dataPath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load(dataPath));
        } catch (BobException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Reads and executes the command given by the user until an exit command is encountered.
     */
    private void runCommandLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(ui, storage, tasks);
                isExit = command.isExit();
            } catch (BobException e) {
                ui.show(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private void run() {
        ui.showWelcome();
        runCommandLoopUntilExitCommand();
    }

    @Override
    public void start(Stage stage) {
        new Bob(Storage.DATA_PATH).run();

        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
