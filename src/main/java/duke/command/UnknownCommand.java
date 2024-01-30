package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that does not match any valid command.
 */
public class UnknownCommand implements Command {
    /**
     * Throws a DukeException to indicate invalid command and suggests possible working commands.
     *
     * @param list Not used.
     * @param ui Not used.
     * @param storage Not used.
     * @throws DukeException Indicates invalid command.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Sorry I don't know what that means.\n"
                + "\t Try keywords: todo, deadline, event, list, mark, unmark, delete.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
