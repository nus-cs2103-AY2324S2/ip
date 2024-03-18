package coat.command;

import java.util.List;

import coat.task.TaskList;
import coat.ui.Messages;
import coat.ui.Ui;

/**
 * A command class representing the action of displaying a list of available commands.
 *
 * <p>The {@code HelpCommand} class encapsulates the information and actions
 * required to display a list of available commands to the user. It inherits from the {@code Command}
 * class and implements the behavior specific to displaying the help message.</p>
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", List.of());
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) {
        String helpMessage = Messages.HELP_SUCCESS.getMessage();

        ui.appendResponse(helpMessage);

        return tasks;
    }
}
