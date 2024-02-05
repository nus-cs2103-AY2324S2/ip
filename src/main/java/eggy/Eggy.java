package eggy;

import eggy.command.Command;
import eggy.exception.EggyException;
import eggy.parser.Parser;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

/**
 * The main class of Eggy chatbot.
 */
public class Eggy {
    /** The name of the chatbot. */
    public final String NAME = "Eggy";
    /** The user interface of the chatbot. */
    private final Ui ui;
    /** The storage of the chatbot. */
    private final Storage storage;
    /** The task list of the chatbot. */
    private TaskList tasks;

    /**
     * Constructs an Eggy chatbot with the given storage path.
     *
     * @param filePath The file path of the storage.
     */
    public Eggy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EggyException e) {
            ui.printException(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.printWelcome(this.NAME);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command c = Parser.parse(fullCommand, tasks.getSize());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (EggyException e) {
                ui.printException(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    /**
     * The main method of the chatbot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Eggy("data/tasks.txt").run();
    }
}
