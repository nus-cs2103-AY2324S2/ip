package xilef.command;

import xilef.task.Task;
import xilef.task.TaskCommandPair;
import xilef.task.TaskList;
import xilef.XilefException;
import xilef.Storage;
import xilef.Ui;
import xilef.Xilef;

public class UndoCommand extends Command {
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
        return ui.showUndo();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
