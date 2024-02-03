package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        String[] parts = userInput.split("\\s+", 2);
        String findTask = parts[1].toLowerCase();
        List<Task> tasks = taskList.getTasks();
        List<Task> filteredList = new ArrayList<>();

        for (Task task : tasks) {
            if (task.toString().contains(findTask)) {
                filteredList.add(task);
            }
        }

        ui.showTasks(filteredList);
    }
}
