package duke.commands;

import duke.exceptions.InvalidIndexException;
import duke.exceptions.StorageException;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

public class MarkTaskDone extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException, StorageException {
        String[] userInput = ui.getCommand();
        int index = Integer.parseInt(userInput[userInput.length - 1]) - 1;
        taskList.getTask(index).markDone();
    }
}
