package ben;

import ben.commands.Command;
import ben.exceptions.BenException;
import ben.parser.Parser;
import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

import java.io.FileNotFoundException;

/**
 * The main class for the Ben task management application.
 */
public class Ben {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ben() {
        // ...
    }

    /**
     * Constructs the Main object with the specified file path for storage.
     *
     * @param filePath The file path for storage.
     */
    public Ben(String filePath) {
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * The main method to start the Ben task management application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Ben("data/tasks.txt").run();
    }
}
