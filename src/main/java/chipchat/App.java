package chipchat;

import chipchat.action.Action;
import chipchat.exception.ChipchatException;
import chipchat.parser.Parser;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

/**
 * Encapsulates the main program application.
 */
public class App {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs the App. Retrieves saved data from storage at initialization.
     */
    public App() {
        this.ui = new Ui();
        this.storage = new Storage();

        TaskList tmp;
        try {
            tmp = new TaskList(storage.load());
        } catch (ChipchatException x) {
            ui.showLoadingError();
            tmp = new TaskList();
        }
        this.tasks = tmp;
    }

    /**
     * Runs the main program through a Read-Eval-Print loop.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readUserInput();
                ui.showLine();
                Action action = Parser.parseAction(userInput);
                action.run(tasks, ui, storage);
                isExit = action.isExit();
            } catch (ChipchatException exc) {
                System.err.println(exc);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Starting point of main program.
     *
     * @param args system input
     */
    public static void main(String[] args) {
        new App().run();
    }
}
