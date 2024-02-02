package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;
import duke.Tasks.Task;



public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (task.getTask().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

