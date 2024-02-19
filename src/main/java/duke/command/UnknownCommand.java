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

    /**
     * Appends a user-friendly error message to the output.
     * @param  tasks The task list.
     * @param  ui    The UI Stringbuilder
     * @return       Tasks.
     */
    public TaskList execute(TaskList tasks, Ui ui) {
        ui.appendResponse("Sorry.. I don't know what that means!"
                    + " If you want to leave, just say 'bye'! :(\n");
        return tasks;
    }
}
