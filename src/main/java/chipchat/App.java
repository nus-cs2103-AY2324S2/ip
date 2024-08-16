package chipchat;

import java.util.List;

import chipchat.action.Action;
import chipchat.exception.ChipchatException;
import chipchat.parser.Parser;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;
import javafx.stage.Stage;

/**
 * Encapsulates the main program application.
 */
public class App {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Stage stage;

    /**
     * Constructs the App. Retrieves saved data from storage at initialization.
     */
    public App(Stage stage) {
        this.stage = stage;
        this.ui = new Ui();
        this.storage = new Storage();

        TaskList tasks = new TaskList();
        List<Action> actions = storage.load();
        for (Action addTask : actions) {
            addTask.run(tasks, ui, null);
        }
        this.tasks = tasks;
        ui.clearBuffer();
    }

    /**
     * Returns the welcome message.
     *
     * @return the welcome message
     */
    public String getGreetings() {
        ui.showWelcome();
        return ui.getOutput();
    }

    /**
     * Returns the app response.
     *
     * @param userInput the user input to be handled
     * @return the response by the app
     */
    public String getResponse(String userInput) {
        run(userInput);
        return ui.getOutput();
    }

    /**
     * Runs the main program through a Read-Eval-Print loop.
     */
    public void run(String userInput) {
        try {
            Action action = Parser.parseAction(userInput);
            action.run(tasks, ui, storage);
            if (action.isExit()) {
                closeApp();
            }
        } catch (ChipchatException exc) {
            ui.showErrMsg(exc);
        }
    }

    private void closeApp() {
        stage.close();
    }
}
