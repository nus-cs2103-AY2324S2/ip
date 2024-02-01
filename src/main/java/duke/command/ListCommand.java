package duke.command;

import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    /**
     * Executes list command.
     *
     * @param tasks List of tasks.
     * @param ui Ui of ai chatbot.
     * @param storage External storage in hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTask();
    }
}
