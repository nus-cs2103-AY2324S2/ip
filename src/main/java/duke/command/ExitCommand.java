package duke.command;

import duke.exception.DukeException;
import duke.util.DukeList;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand implements Command {
    /**
     * Displays exit message.
     *
     * @param dukeList Holds the tasks added.
     * @param ui Display messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException Not thrown.
     */
    @Override
    public String execute(DukeList dukeList, Ui ui, Storage storage) throws DukeException {
        TaskList list = (TaskList) dukeList;
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
