package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Represents a command to print all task in taskList.
 */
public class PrintListCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for the PrintListCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     */
    public PrintListCommand(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    @Override
    public String execute() throws DukeException {
        return this.ui.printList(this.taskList);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
