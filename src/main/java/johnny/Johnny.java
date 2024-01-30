package johnny;

import johnny.commands.Command;
import johnny.exceptions.JohnnyException;
import johnny.parser.Parser;
import johnny.storage.Storage;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * The chatbot Johnny.
 * It takes in specified commands to manage Tasks.
 */
public class Johnny {

    /** Storage component to read and store Tasks in a text file. */
    private Storage storage;

    /** TaskList component to store tasks in a list. */
    private TaskList tasks;
    /** Ui component that reads commands and prints responses to user. */
    private Ui ui;

    /**
     * Constructor for Johnny chatbot.
     * Instantiates Ui and Storage component.
     * Instantiates TaskList component using data in the Storage.
     * Ui will print any error caught in the chatbot to the user.
     *
     * @param filePath File path to where the data will be stored.
     */
    public Johnny(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (JohnnyException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot.
     * Print welcome message and reads command from user and responds with the appropriate response.
     * Ui will print any error caught in the chatbot to the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JohnnyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Instantiates a new chatbot with a specified file path and starts the chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Johnny("data/tasks.txt").run();
    }

}
