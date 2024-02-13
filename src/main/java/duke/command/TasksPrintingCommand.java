package duke.command;

import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * PrintCommand class
 */
public class TasksPrintingCommand extends Command {
    /**
     * Returns print command.
     *
     * @param tasks List of tasks.
     * @param ui Ui of ai chatbot.
     * @param storage External storage in hard disk.
     */
    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage) {
        return ui.getCommandList();
    }
}
