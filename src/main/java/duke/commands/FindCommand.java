package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE = "found for u liao:\n%s";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        TaskList filteredTasks = new TaskList("");

        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        ui.print(String.format(MESSAGE, filteredTasks.toString()));
    }
}
