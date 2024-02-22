package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to display helps.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new ExitCommand object.
     */
    public HelpCommand() {

    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return "OOPS! This command doesn't exist. Retry! "
                + "Acceptable commands are: todo, deadline, event, list, mark, unmark, save, delete, find, view, bye";
    }
}
