package mychats.command;
import mychats.exception.MyChatsException;
import mychats.main.Storage;
import mychats.main.TaskList;
import mychats.main.Ui;

/**
 * Represents a command to undo the most recent command.
 */
public class UndoCommand extends Command {

    /**
     * Executes the UndoCommand by removing the most recent command.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws MyChatsException If the task list is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MyChatsException {
        tasks.undoRecentTask();
        ui.displayListAfterUndo(tasks.getTasks());
        storage.saveList(tasks.getTasks());
    }
}
