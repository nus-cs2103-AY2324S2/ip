package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Class representing Delete commands.
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        assert index >= 0 : "index should not be negative";
        this.index = index;
    }
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task deleted = tasks.delete(index);
        return "OK! I've deleted this task:\n" + deleted.toString() +
                "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
}
