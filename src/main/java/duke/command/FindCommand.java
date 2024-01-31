package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

public class FindCommand extends Command {

    private final String param;

    public FindCommand(String param) {
        this.param = param;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
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
