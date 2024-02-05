import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Tasklist;
import duke.ui.Ui;

/**
 * The main class of the Duke application.
 * Runs the application.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private Tasklist tasklist;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        tasklist = new Tasklist();
        ui = new Ui();
        storage = new Storage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        boolean isBye = false;
        while (!isBye) {
            String command = ui.getCommand();
            isBye = parser.parseCommand(command, ui, storage);
        }
        ui.printByeMessage();
    }
}
