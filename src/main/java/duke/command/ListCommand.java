package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The `ListCommand` class represents a command that lists all tasks in the task list.
 * It provides methods to lists all tasks in the task list, and to decide whether the program should continue.
 * It extends the `Command` class.
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     *
     * @param tasks Existing tasks.
     * @param ui The Ui of the program.
     * @param storage The storage of the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.list(tasks);
    }

    /**
     * Returns False.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
