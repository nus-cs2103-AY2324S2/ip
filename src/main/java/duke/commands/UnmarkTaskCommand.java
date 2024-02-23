package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkTaskCommand extends Command {
    private int index;

    /**
     * Constructs a new UnmarkTaskCommand object with the given task index.
     *
     * @param number The index of the task to be unmarked.
     */
    public UnmarkTaskCommand(int number) {
        this.index = number;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            return "Please look carefully. This task is not inside the task list.";
        }
        return tasks.get(index).unmark(ui);
    }
}



