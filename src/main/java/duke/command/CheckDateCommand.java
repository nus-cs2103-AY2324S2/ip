package duke.command;

import duke.exception.DukeException;
import duke.helpers.FileManaging;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

import java.io.IOException;

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

    /**
     * Executes check date command.
     *
     * @param tasks List of tasks.
     * @param ui Ui of ai chatbot.
     * @param storage External storage in hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.checkDate(commandArr.length > 1 ? commandArr[1] : "");
    }

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage) {
        return tasks.checkDate(commandArr.length > 1 ? commandArr[1] : "");
    }
}
