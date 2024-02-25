package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert ui != null;
        List<Task> taskList = tasks.getTasks();
        taskList.sort(Task::compareTo);
        ui.showTasks(taskList);
    }
}
