package Commands;

import TaskLists.TaskList;
import Tasks.Task;
import UiRelated.Ui;


/**
 * The addToListCommand class represents a command to add a task to the list.
 */
public class AddToListCommand extends Command {
    private final Task task;

    /**
     * Constructs an addToListCommand object with the specified task.
     *
     * @param t The task to be added to the list.
     */
    public AddToListCommand(Task t) {
        task = t;
    }

    /**
     * Executes the addToListCommand by adding the task to the TaskList and displaying a message in the Ui.
     *
     * @param taskList The TaskList object containing the tasks.
     * @param ui       The Ui object for displaying messages.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addToList(task);
        ui.display("HASSNT:\n" + "Got it. I've added this task:  " + task);
        ui.display("\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
