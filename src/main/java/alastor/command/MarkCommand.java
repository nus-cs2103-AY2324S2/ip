package alastor.command;

import alastor.AlastorException;
import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;
import alastor.task.Task;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isMark;

    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlastorException {
        if (index < 1 || index > tasks.size()) {
            throw new AlastorException("I'm afraid the task number you've entered is invalid, my dear.");
        }
        Task task = tasks.get(index - 1);
        if (isMark) {
            task.mark();
        } else {
            task.unmark();
        }
        ui.showMark(task, this.isMark);
        storage.saveRewrite(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
