package duke;

import java.io.IOException;

/**
 * Main class for the Duke task management application.
 * This class is responsible for initializing the application, processing input commands,
 * and maintaining the main application loop.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Duke object.
     * Initializes the user interface, storage, and task list components of the application.
     * Tries to load existing tasks from the storage; if unsuccessful, starts with an empty task list.
     *
     * @param filePath The file path where tasks are stored and retrieved from.
     */
    public Duke(String filePath) throws DukeException {
        Ui ui = new Ui();
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
     */
    public String run(String command) {
        String dukeResponse = "";
        if (command.equals("bye")) {
            try {
                storage.save(tasks);
            } catch (IOException e) {
                return "Error saving tasks to file";
            }
            dukeResponse = Ui.exit();
        } else {
            try {
                dukeResponse = Ui.checkCmd(this.tasks, command);
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        }
        return dukeResponse;
    }
}
