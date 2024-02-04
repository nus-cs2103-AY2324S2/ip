package lrbg.codriver;

import lrbg.codriver.command.Command;
import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.parser.Parser;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * The CoDriver program is a task manager that helps users keep track of their tasks.
 */
public class CoDriver {
    /** The storage object that handles the loading and saving of tasks. */
    private Storage storage;
    /** The task list that stores the tasks. */
    private TaskList tasks;
    /** The user interface that interacts with the user. */
    private Ui ui;

    /**
     * Constructs a CoDriver object.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public CoDriver(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showFileLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the CoDriver program.
     */
    public void run() {
        ui.showGreeting();
        while (true) {
            String commandLine = ui.readCommand();
            ui.showLine();
            try {
                Command c = Parser.parse(commandLine);
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (CoDriverException e) {
                ui.showError(e);
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } finally {
                ui.showLine();
            }
        }
        ui.close();
        storage.save(this.tasks.toSaveString());
    }

    public static void main(String[] args) {
        new CoDriver("./data/codriver.txt").run();
    }
}
