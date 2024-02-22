package alastor.command;

import alastor.AlastorException;
import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;
import alastor.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlastorException {
        if (index < 1 || index > tasks.size()) {
            throw new AlastorException("I'm afraid the task number you've entered is invalid, my dear.");
        }
        Task task = tasks.get(index - 1);
        tasks.delete(index - 1);
        storage.saveRewrite(tasks);
        return ui.showDelete(task);
    }
}
