package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The `ExitCommand` class represents a command that exit the program.
 * It provides methods to exit the program, and to decide whether the program should continue.
 * It extends the `Command` class.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command.
     *
     * @param tasks Existing tasks.
     * @param ui The Ui of the program.
     * @param storage The storage of the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }

    /**
     * Returns True.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
