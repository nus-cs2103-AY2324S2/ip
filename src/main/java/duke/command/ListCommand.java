package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This class extends the Command class and overrides the execute method to print a list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructs a new ListCommand object.
     * This constructor does not require any parameters.
     */
    public ListCommand() {
        //do nothing
    }

    /**
     * Method that lists the task from the current task list.
     *
     * @param tasks The TaskList object on which the command will operate
     * @param storage The Storage object that will read and write to files
     * @param ui The Ui object that handles displaying messages
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        super.tasks = tasks;
        super.storage = storage;
        super.ui = ui;
        return super.ui.displayList("Behold, yer roster of endeavors!", super.tasks.getTasks());
    }
}
