package duke.command;

import java.util.List;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing an unknown / undefined command.
 *
 * <p>The {@code UnknownCommand} class encapsulates the information and actions
 * required to handle an unknown command. It inherits from the {@code Command} class
 * and represents a command that is not recognized by the Duke chatbot.</p>
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {
        super("", List.of());
    }

    public TaskList execute(TaskList tasks, Ui ui) {
        return null;
    }
}
