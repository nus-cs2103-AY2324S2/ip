package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkTaskCommand extends Command {
    private int index;

    /**
     * Constructs a new MarkTaskCommand object with the given task index.
     *
     * @param number The index of the task to be marked as completed.
     */
    public MarkTaskCommand(int number) {
        this.index = number;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (index >= tasks.size() || index < 0) {
            return "Please look carefully. This task is not inside the task list.";
        }
        return tasks.get(index).markDone(ui);
    }
}
