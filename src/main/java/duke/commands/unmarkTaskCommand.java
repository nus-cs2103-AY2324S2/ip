package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task.
 */
public class unmarkTaskCommand extends Command{
    private int index;

    /**
     * Constructs a new UnmarkTaskCommand object with the given task index.
     *
     * @param number The index of the task to be unmarked.
     */
    public unmarkTaskCommand(int number) {
        this.index = number;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            System.err.println("Please look carefully. This task is not inside the task list.");
            return false;
        }
        tasks.get(index).unmark(ui);
        return true;
    }
}



