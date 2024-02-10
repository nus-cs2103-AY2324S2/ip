package lindi;

import lindi.commands.Command;
import lindi.gui.DialogBox;
import lindi.parser.Parser;
import lindi.storage.Storage;
import lindi.storage.StorageLoadException;
import lindi.task.TaskList;
import lindi.ui.Ui;

/**
 * Entry point of Lindi Application.
 * Initializes the application and starts the interaction with the user.
 */
public class Lindi {
    // Lindi private attributes for chatbot logic
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final String name = "Lindi"; // Log It N Do It -> LINDI

    /**
     * No argument constructor for JavaFx compatibility
     */
    public Lindi() {
        this.storage = new Storage("./.data", "LindiData.txt"); // hard-coded data file path
        this.ui = new Ui(this.name);
        try {
            this.tasks = this.storage.loadFromFile();
        } catch (StorageLoadException e) {
            this.ui.displayError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Initializes Lindi
     *
     * @param dataDir directory of data file
     * @param dataFileName name of data file
     */
    public Lindi(String dataDir, String dataFileName) {
        this.storage = new Storage(dataDir, dataFileName);
        this.ui = new Ui(this.name);
        try {
            this.tasks = this.storage.loadFromFile();
        } catch (StorageLoadException e) {
            this.ui.displayError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the program. This loops until terminated by user with ExitCommand.
     */
    public void run() {
        this.ui.greeting();
        boolean toExit = false;

        while (!toExit) {
            String userInput = this.ui.getUserInput();

            Command c = Parser.parse(userInput);
            c.execute(this.tasks, this.storage);
            this.ui.displayCommand(c);

            toExit = c.isExit();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(this.tasks, this.storage);
        String response = c.status();
        return response;
    }

    public static void main(String[] args) {
        new Lindi("./.data", "LindiData.txt").run();
    }
}
