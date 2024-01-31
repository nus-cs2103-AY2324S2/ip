package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class for the Duke task management application.
 * This class is responsible for initializing the application, processing input commands,
 * and maintaining the main application loop.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Duke object.
     * Initializes the user interface, storage, and task list components of the application.
     * Tries to load existing tasks from the storage; if unsuccessful, starts with an empty task list.
     *
     * @param filePath The file path where tasks are stored and retrieved from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Continuously reads user input commands and processes them until the "bye" command is entered.
     * Before exiting, saves the current state of tasks to the storage.
     *
     * @throws IOException If an I/O error occurs while reading user input.
     */
    public void run() throws IOException {
        ui.greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (!(command = br.readLine()).equals("bye")) {
            try {
                Parser.checkCmd(this.tasks, command);
            } catch (DukeException de){
                Ui.beautify(de.getMessage());
            }
        }
        storage.save(tasks);
        ui.exit();
    }

    /**
     * The entry point of the application.
     * Creates a new Duke instance and runs it.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs in the run method.
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}