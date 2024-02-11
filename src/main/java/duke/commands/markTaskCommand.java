package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class markTaskCommand extends Command{
    private int index;

    /**
     * Constructs a new MarkTaskCommand object with the given task index.
     *
     * @param number The index of the task to be marked as completed.
     */
    public markTaskCommand(int number) {
        this.index = number;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            System.err.println("Please look carefully. This task is not inside the task list.");
            return false;
        }
        tasks.get(index).markDone(ui);
        return true;
    }
}
