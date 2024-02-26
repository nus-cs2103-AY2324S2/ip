package duke.commands;

import duke.exceptions.*;
import duke.fileUtils.FileUtil;
import duke.mainUtils.Parser;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The CreateDeadlineTask class represents a command to create a deadline task.
 * <p>
 * This command parses the user input to create a deadline task and adds it to the task list.
 * </p>
 * <p>
 * It extends the Command class and implements the execute method to perform its action.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.commands.Command
 * @see duke.mainUtils.Parser
 * @see duke.mainUtils.Storage
 * @see duke.mainUtils.Ui
 * @see duke.tasks.TaskList
 * @see duke.exceptions.InvalidTaskException
 * @see duke.exceptions.InvalidDateException
 * @see duke.exceptions.StorageException
 */
public class CreateDeadlineTask extends Command {
    private Task task;

    /**
     * Executes the command to create a deadline task.
     *
     * @param taskList the task list to add the new task to.
     * @param ui the user interface for interacting with the user.
     * @param storage the storage for saving the task.
     * @throws InvalidTaskException if the task is invalid.
     * @throws InvalidDateException if the date format is invalid.
     * @throws StorageException if there is an error accessing the storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException, StorageException, InvalidIndexException, TaskNotFoundException {
        this.task = Parser.parseDeadlineTask(ui.getCommand());
        taskList.addTask(task);
        return doneExecute(taskList, ui, storage);
    }

    @Override
    public String doneExecute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException, InvalidIndexException, StorageException, TaskNotFoundException {
        return String.format("More tasks? Someone's being very ambitious here. I like. Added:%n %s %nYou now have %d tasks total!",
                this.task.toString(), taskList.size());
    }
}
