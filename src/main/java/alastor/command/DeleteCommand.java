package alastor.command;

import alastor.AlastorException;
import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;
import alastor.task.Task;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlastorException {
        if (index < 1 || index > tasks.size()) {
            throw new AlastorException("I'm afraid the task number you've entered is invalid, my dear.");
        }
        Task task = tasks.get(index - 1);
        tasks.delete(index - 1);
        ui.showDelete(task);
        storage.saveRewrite(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
