package brian.command;

import brian.storage.Storage;
import brian.task.Task;
import brian.task.TaskList;
import brian.task.Todo;
import brian.ui.TextUi;

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
