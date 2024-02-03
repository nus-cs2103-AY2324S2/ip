package duke;

import duke.command.Command;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Duke program implements an application that
 * manages a list of tasks. It allows adding, deleting,
 * and completing tasks, as well as listing all the current tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke object.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            System.exit(0);
        } catch (JamieException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application.
     * Reads user input and executes commands until the user exits.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void run() throws IOException {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (JamieException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExitMessage();
        scanner.close();
    }

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     * @throws IOException If an I/O error occurs during the run.
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/Jamie.txt").run();
    }
}
