package commands;

import exceptions.InvalidDateException;
import exceptions.InvalidIndexException;
import exceptions.InvalidTaskException;
import exceptions.StorageException;
import mainUtils.Storage;
import mainUtils.Ui;
import tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException, InvalidIndexException, StorageException;
}
