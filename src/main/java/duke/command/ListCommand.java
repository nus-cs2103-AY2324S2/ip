package duke.command;

import duke.exception.DukeException;
import duke.helpers.FileManaging;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

import java.io.IOException;

/**
 * ListCommand class
 */
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

    @Override
    public String getExecuteMessage(TaskList tasks, Ui ui, Storage storage) {
        return tasks.getListTask();
    }
}
