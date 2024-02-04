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
}
