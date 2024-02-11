package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to display helps.
 */
public class helpCommand extends Command{

    /**
     * Constructs a new ExitCommand object.
     */
    public helpCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        System.err.println("Acceptable commands are: todo, deadline, event, list, mark, unmark, save, delete, bye");
        return true;
    }
}
