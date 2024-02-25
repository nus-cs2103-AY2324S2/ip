package brian.command;

import java.util.List;
import java.util.stream.Collectors;

import brian.storage.Storage;
import brian.task.Task;
import brian.task.TaskList;
import brian.ui.TextUi;

public class FindCommand extends Command {

    private final String param;

    public FindCommand(String param) {
        this.param = param;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert tasks != null;
        assert ui != null;

        if (param == "") {
            ui.foundTasks(tasks.getTasks());
            return;
        }

        List<Task> filteredList = tasks.getTasks().stream()
                .filter(task -> task.getTask().contains(param))
                .collect(Collectors.toList());
        ui.foundTasks(filteredList);
    }

}
