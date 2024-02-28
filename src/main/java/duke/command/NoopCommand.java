package duke.command;

import java.util.List;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing a no-operation (noop) command.
 *
 * <p>The {@code NoopCommand} class encapsulates the information and actions
 * required for a command that does nothing. It inherits from the {@code Command}
 * class and implements the behavior specific to a no-operation command.</p>
 */
public class NoopCommand extends Command {
    public NoopCommand() {
        super("noop", List.of());
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) {
        // No operation, do nothing
        return tasks;
    }
}
