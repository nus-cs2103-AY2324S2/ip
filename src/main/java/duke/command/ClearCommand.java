package duke.command;

import java.util.List;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing the action of clearing the task list.
 *
 * <p>The {@code ClearCommand} class encapsulates the information and actions
 * required to clear all tasks from the task list. It inherits from the {@code Command}
 * class and implements the behavior specific to clearing the task list.</p>
 */
public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", List.of());
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) {
        tasks.clearTasks();
        ui.appendResponse("All tasks cleared.");
        return tasks;
    }
}
