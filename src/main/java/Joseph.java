import duke.exception.JosephException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Tasklist;
import duke.ui.Ui;

/**
 * The main class of the Duke application.
 * Runs the application.
 */
public class Joseph {
    private Ui ui;
    private Storage storage;
    private Tasklist todolist;
    private Parser parser;

    /**
     * Constructor for the Duke class.
     */
    public Joseph() {
        try {
            todolist = new Tasklist();
            ui = new Ui();
            storage = new Storage();
            parser = new Parser();
        } catch (JosephException e) {
            ui.printMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Joseph duke = new Joseph();
        duke.run();
    }

    /**
     * Runs the Duke application.
     * Keeps receiving commands from the user and executing them until the user types "bye".
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean commandIsBye = false;
        while (!commandIsBye) {
            String command = ui.getCommand();
            try {
                commandIsBye = parser.commandIsBye(command);
                parser.parseCommand(command, ui, storage, todolist);
            } catch (JosephException e) {
                ui.printMessage(e.getMessage());
            }
        }
        ui.printByeMessage();
    }
    public String getResponse(String input) {
        try {
            return parser.parseCommand(input, ui, storage, todolist);
        } catch (JosephException e) {
            ui.printMessage(e.getMessage());
            return "Error";
        }
    }
}
