package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a new DeleteCommand object with the given task index.
     *
     * @param number The index of the task to be deleted.
     */
    public DeleteCommand(int number) {
        this.index = number;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            return "Please look carefully. This task is not inside the task list.";
        }
        String taskStr = tasks.get(index).toString();
        tasks.deleteTask(this.index);
        return "Noted. I've removed this task:\n  " + taskStr + "\nNow you have "
                + tasks.size() + " tasks in the list.";
    }
}
