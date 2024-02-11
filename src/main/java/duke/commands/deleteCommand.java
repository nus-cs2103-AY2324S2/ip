package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class deleteCommand extends Command{
    private int index;

    /**
     * Constructs a new DeleteCommand object with the given task index.
     *
     * @param number The index of the task to be deleted.
     */
    public deleteCommand(int number) {
        this.index = number;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            System.err.println("Please look carefully. This task is not inside the task list.");
            return false;
        }
        String taskStr = tasks.get(index).toString();
        tasks.deleteTask(this.index);
        ui.showLine();
        System.out.println("Noted. I've removed this task:\n  " + taskStr);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        return true;
    }
}
