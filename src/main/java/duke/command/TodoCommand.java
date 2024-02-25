package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.TextUi;

public class TodoCommand extends Command {
    private final String todo;

    public TodoCommand(String command) {
        this.todo = command;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert ui != null;
        assert tasks != null;
        Task curr = new Todo(todo);
        tasks.add(curr);
        ui.showTask(curr, tasks.size());
    }
}
