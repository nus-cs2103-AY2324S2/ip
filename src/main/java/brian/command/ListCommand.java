package brian.command;

import java.util.List;

import brian.storage.Storage;
import brian.task.Task;
import brian.task.TaskList;
import brian.ui.TextUi;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert ui != null;
        List<Task> taskList = tasks.getTasks();
        taskList.sort(Task::compareTo);
        ui.showTasks(taskList);
    }
}
