package duke;

import duke.commands.Command;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;



import static duke.constants.Constant.RELATIVE_PATH;

/**
 * The main class of the Duke application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Constructs a new Duke object with the specified file path.
     *
     * @param filePath The file path to load and store task data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.getUserCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(ui, tasks);
            isExit = c.isExit();
        }
    }

    /**
     * Displays welcome message to user when first load.
     */
    public String showWelcome() {
        assert ui != null : "Ui object should not be null";
        return ui.showWelcome();
    }

    /**
     * Gets output responses for input.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(ui, tasks);
    }

    /**
     * Runs the entry point of the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke(RELATIVE_PATH).run();
    }


}
