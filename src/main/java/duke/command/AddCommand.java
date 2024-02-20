package duke.command;

import duke.MyList;
import duke.Task;
import duke.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the task to the task list and adding
     * response to the UI.
     *
     * @param myList The list to which the task will be added.
     * @param ui     The user interface for displaying results.
     */
    public void execute(MyList myList, Ui ui) {
        String addedTaskMessage = myList.addTask(task);
        ui.addResponse(addedTaskMessage);
    }
}
