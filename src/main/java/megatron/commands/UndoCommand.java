package megatron.commands;

import megatron.data.exception.DukeException;
import megatron.storage.Storage;
import megatron.task.TaskList;
import megatron.ui.Ui;

/**
 * Command subclass for exiting chatbot
 */
public class UndoCommand extends Command {
    private final Command commandToUndo;

    public UndoCommand(Command commandToUndo) {
        this.commandToUndo = commandToUndo;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return commandToUndo.undo(tasks, ui, storage);
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.lastCommandUndoed();
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
