package alpa.main;

import alpa.commands.*;
import alpa.exceptions.*;
import alpa.tasks.*;
import alpa.ui.*;
import alpa.utils.*;

/**
 * Represents the main class of the Alpa application.
 * Alpa is a task management application that helps users keep track of their tasks.
 */
public class Alpa {
    private static final String FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Represents the main class of the Alpa application.
     */
    public Alpa() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList();
            tasks.addAll(storage.loadTasks());
        } catch (AlpaException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Alpa program.
     * Displays a welcome message and enters a loop to read and execute user commands until the user chooses to exit.
     * Each command is parsed and executed using the provided tasks, ui, and storage objects.
     * If an AlpaException is thrown during command execution, an error message is displayed.
     */
    public void runAlpa() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.executeCommand(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AlpaException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the Alpa application.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        new Alpa().runAlpa();
    }
}
