package xilef.command;

import xilef.task.Task;
import xilef.task.TaskCommandPair;
import xilef.task.TaskList;
import xilef.XilefException;
import xilef.Storage;
import xilef.Ui;
import xilef.Xilef;

/**
 * Represents a command to undo the last action performed in the application.
 */
public class UndoCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Undoes the last action performed in the application.
     * If the last action was adding a task, it removes the task.
     * If the last action was deleting a task, it adds the task back.
     * If the last action was marking a task as done, it marks the task as not done.
     * If the last action was unmarking a task, it marks the task as done.
     *
     * @return A string indicating that the undo operation was successful.
     * @throws XilefException If there is nothing to undo.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws XilefException {
        TaskCommandPair pair = Xilef.pop();
        Task task = pair.getTask();
        Command c = pair.getCommand();
        if (c instanceof AddCommand) {
            tasks.remove(task);
        } else if (c instanceof DeleteCommand) {
            tasks.add(task);
        } else if (c instanceof MarkCommand) {
            task.unmarkDone();
        } else {
            task.markDone();
        }
        storage.save(tasks);
        return ui.showUndo();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
