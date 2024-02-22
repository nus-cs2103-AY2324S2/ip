package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.SamException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String taskInfo) throws SamException {
        if (taskInfo.isBlank()) {
            throw new SamException("Please provide the task number to delete.");
        }
        this.index = Integer.parseInt(taskInfo) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        tasks.deleteTask(index);
        storage.save(tasks);
    }
}