package duke.command;

import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * CheckDateCommand class
 */
public class CheckDateCommand extends Command {
    private String[] commandArr;

    /**
     * Constructor of CheckDataCommand.
     *
     * @param commandArr Array of commands.
     */
    public CheckDateCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage, Storage secondaryStorage) {
        return tasks.checkDate(commandArr.length > 1 ? commandArr[1] : "");
    }
}
