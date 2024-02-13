package duke.command;

import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * PrintCommand class
 */
public class PrintCommand extends Command {
    /**
     * Executes print command.
     *
     * @param tasks List of tasks.
     * @param ui Ui of ai chatbot.
     * @param storage External storage in hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printCommandList();
    }

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
