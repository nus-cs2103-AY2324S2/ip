package commands;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;
import exceptions.StorageException;
import mainUtils.Parser;
import mainUtils.Storage;
import mainUtils.Ui;
import tasks.TaskList;

public class CreateDeadlineTask extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException, StorageException {
        taskList.addTask(Parser.parseDeadlineTask(ui.getCommand()));
    }

}
