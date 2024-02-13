package commands;

import exceptions.InvalidIndexException;
import exceptions.StorageException;
import mainUtils.Storage;
import mainUtils.Ui;
import tasks.TaskList;

public class MarkTaskDone extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException, StorageException {
        String[] userInput = ui.getCommand();
        int index = Integer.parseInt(userInput[userInput.length - 1]) - 1;
        taskList.getTask(index).markDone();
    }
}
