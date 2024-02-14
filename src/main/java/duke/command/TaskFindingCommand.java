package duke.command;

import duke.exception.DukeException;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * FindCommand class
 */
public class TaskFindingCommand extends Command {
    private String[] commandArr;

    /**
     * Constructor of delete command.
     *
     * @param commandArr Array of commands.
     */
    public TaskFindingCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage) {
        try {
            return tasks.findTask(commandArr.length > 1 ? commandArr[1] : "");
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
