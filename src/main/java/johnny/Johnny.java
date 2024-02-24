package johnny;

import javafx.application.Platform;
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

    /** File path to where the data will be stored. */
    private static final String FILE_PATH = "data/tasks.txt";
    /** Storage component to read and store Tasks in a text file. */
    private Storage storage;
    /** TaskList component to store tasks in a list. */
    private TaskList tasks;
    /** Ui component determines responses to user. */
    private Ui ui;

    /**
     * Constructor for Johnny chatbot.
     * Instantiates Ui and Storage component.
     * Instantiates TaskList component using data in the Storage.
     * Ui will print any error caught in the chatbot to the user.
     */
    public Johnny() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);

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
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
            return ui.getOutput();
        } catch (JohnnyException e) {
            ui.showError(e.getMessage());
            return ui.getOutput();
        }
    }

    /**
     * Generates the welcome message in the Ui and outputs it.
     *
     * @return Welcome message.
     */
    public String getWelcome() {
        ui.showWelcome();
        return ui.getOutput();
    }

}
