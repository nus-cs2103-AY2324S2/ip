package core;

import java.nio.file.Paths;

import commands.Command;
import data.Storage;
import tasks.TaskList;

/**
 * The Ragdoll class is the main class that manages the execution of a command-line application.
 */
public class Ragdoll {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Ragdoll instance with the given file path for data storage.
     *
     * @param filePath The file path where data is stored.
     */
    public Ragdoll(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.load());
    }

    /**
     * The main entry point of the Ragdoll application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Ragdoll(Paths.get("data", "tasks.txt").toString()).run();
    }

    /**
     * Runs the Ragdoll application by displaying a welcome message, processing user commands,
     * and executing corresponding actions.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            ui.showLine();

            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();

            ui.showLine();
        }
    }
}
