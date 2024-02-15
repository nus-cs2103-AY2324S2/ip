package seiki.commands;

import seiki.data.TaskList;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'list' command.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.showList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
