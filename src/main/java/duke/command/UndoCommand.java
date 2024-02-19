package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskCommandPair;
import duke.task.TaskList;

public class UndoCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskCommandPair pair = Duke.pop();
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
