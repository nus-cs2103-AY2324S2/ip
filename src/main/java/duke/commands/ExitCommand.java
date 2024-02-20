package duke.commands;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command subclass for exiting chatbot
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.nothingToUndo();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean getIsUndoable() {
        return false;
    }
}
