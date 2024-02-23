package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list down all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new ExitCommand object.
     */
    public ListCommand() {

    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.displayTasks();
    }
}
