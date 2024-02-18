package adam;

import adam.command.Command;
import adam.task.TaskList;
import adam.ui.Ui;

/**
 * The Duke program implements a chatBot that keeps track of tasks for the user.
 */
public class Adam {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Returns an instance of the program which loads the tasks from the file found at the provided filepath.
     *
     * @param filePath The filepath of the file to load/saves the tasks from/to.
     */
    public Adam(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AdamException e) {
            tasks = new TaskList();
        }
    }

    public String welcome() {
        return ui.showWelcome();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (AdamException e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
