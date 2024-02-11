package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task deleted = tasks.delete(index);
        return "OK! I've deleted this task:\n" + deleted.toString() +
                "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
}
