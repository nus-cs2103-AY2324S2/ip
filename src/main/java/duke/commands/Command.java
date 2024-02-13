package duke.commands;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.StorageException;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException, InvalidIndexException, StorageException;
}
