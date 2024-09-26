package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Command to remind user about upcoming deadlines.
 */
public class ReminderCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for the ReminderCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     */
    public ReminderCommand(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    @Override
    public String execute() throws DukeException {
        return this.ui.printReminder(this.taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
