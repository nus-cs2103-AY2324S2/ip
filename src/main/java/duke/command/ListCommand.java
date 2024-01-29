package duke.command;

import duke.exception.ChatBotParameterException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * @param storage
     * @param ui
     * @param taskList
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showTaskList(taskList);
    }
}
