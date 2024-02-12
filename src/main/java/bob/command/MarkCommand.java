package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.InvalidTaskIndexException;
import bob.exception.SavingException;
import bob.task.Task;

public class MarkCommand extends Command {
    int taskIndex;
    boolean isDone;

    public MarkCommand(int taskIndex, boolean isDone) {
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws InvalidTaskIndexException, SavingException {
        Task task = taskList.mark(taskIndex, isDone);
        ui.showMark(task, task.getDone());
        taskList.updateStorage(storage);
    }
}
