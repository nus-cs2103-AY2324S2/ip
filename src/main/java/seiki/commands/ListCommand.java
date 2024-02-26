package seiki.commands;

import seiki.data.TaskList;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'list' command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_FORMAT = COMMAND_WORD;
    public static final String COMMAND_HELPER = "Please only type '" + COMMAND_WORD + "' to view your list.";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Lists down all the stored task(s).\n"
            + "Example: " + COMMAND_WORD;
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.showList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
