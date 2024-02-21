package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The `FindCommand` class represents a command that finds a task in the task list.
 * It provides methods to finds a task in the task list, and to decide whether the program should continue.
 * It extends the `Command` class.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a command that finds all task containing a certain keyword when executed.
     *
     * @param task The full user input.
     */
    public FindCommand(String task) {
        this.keyword = task.substring(5);
    }

    /**
     * Executes the command.
     *
     * @param tasks Existing tasks.
     * @param ui The Ui of the program.
     * @param storage The storage of the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.list(tasks, this.keyword);
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
