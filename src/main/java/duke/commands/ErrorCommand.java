package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Represents a command to print an error.
 */
public class ErrorCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private String error;

    /**
     * Constructor for the ErrorCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     * @param error The error message to be printed.
     */
    public ErrorCommand(Ui ui, TaskList taskList, Storage storage, String error) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.error = error;
    }
    @Override
    public String execute() throws DukeException {
        return this.ui.printErrorMessage(this.error);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
