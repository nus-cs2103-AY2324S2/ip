package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IllegalParamException;
import duke.task.TaskList;


/**
 * Commands are a family of classes that represent commands from the user.
 *  defining execute method.
 */
public abstract class Command {
    /**
     * Executes actions to complete the duke.command.
     *
     * @param list a duke.command.task.TaskList object containing current tasks.
     * @param ui a duke.Ui object. Receives instructions on how to update the user interface.
     * @param storage duke.Storage object. For saving changes to memory.
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws IllegalParamException;

}
