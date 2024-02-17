package duke.commands;

import duke.exceptions.*;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

/**
 * The Command class represents an abstract command in the Duke application.
 * <p>
 * A command performs an action on the task list, UI, or storage, typically invoked by the user.
 * </p>
 * <p>
 * Subclasses of Command implement specific actions such as adding a task, listing tasks, marking tasks as done, etc.
 * </p>
 * <p>
 * Each subclass must implement the execute method to perform its specific action.
 * </p>
 * <p>
 * Commands can interact with the task list, UI, and storage to execute their actions.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.tasks.TaskList
 * @see duke.mainUtils.Ui
 * @see duke.mainUtils.Storage
 * @see duke.exceptions.InvalidTaskException
 * @see duke.exceptions.InvalidDateException
 * @see duke.exceptions.InvalidIndexException
 * @see duke.exceptions.StorageException
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param taskList the task list to operate on.
     * @param ui the user interface for displaying messages.
     * @param storage the storage for reading from and writing to files.
     * @throws InvalidTaskException if the task is invalid.
     * @throws InvalidDateException if the date is invalid.
     * @throws InvalidIndexException if the index is invalid.
     * @throws StorageException if there is an error accessing the storage.
     * @throws TaskNotFoundException if the task is not found.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException, InvalidIndexException, StorageException, TaskNotFoundException;
}
