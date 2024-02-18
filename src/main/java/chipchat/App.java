package chipchat;

import chipchat.action.Action;
import chipchat.exception.ChipchatException;
import chipchat.parser.Parser;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

import java.util.List;

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

        TaskList tasks = new TaskList();
        List<Action> actions = storage.load();
        for (Action addTask : actions) {
            addTask.run(tasks, ui, null);
        }
        ui.clearBuffer();
        this.tasks = tasks;
    }

    public String getGreetings() {
        ui.showLine();
        ui.showWelcome();
        ui.showLine();
        return ui.getOutput();
    }

    public String getResponse(String userInput) {
        run(userInput);
        return ui.getOutput();
    }

    /**
     * Runs the main program through a Read-Eval-Print loop.
     */
    public void run(String userInput) {
        try {
            ui.showLine();
            Action action = Parser.parseAction(userInput);
            action.run(tasks, ui, storage);
        } catch (ChipchatException exc) {
            ui.showErrMsg(exc);
        } finally {
            ui.showLine();
        }
    }
}
