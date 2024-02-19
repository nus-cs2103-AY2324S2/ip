package duke.command;

import java.util.List;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing the action of listing all tasks.
 *
 * <p>The {@code ListCommand} class encapsulates the information and actions
 * required to list all tasks stored in the task list. It inherits from the {@code Command}
 * class and implements the behavior specific to listing tasks.</p>
 */
public class ListCommand extends Command {
    private static final String TASKS_MESSAGE = "\nHere's your list! ~~(^-^)\n";

    public ListCommand() {
        super("list", List.of());
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) {
        ui.appendResponse(TASKS_MESSAGE);
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            ui.appendResponse(String.format("%d. %s\n", i + 1, tasks.getTask(i)));
        }
        return tasks;
    }
}
