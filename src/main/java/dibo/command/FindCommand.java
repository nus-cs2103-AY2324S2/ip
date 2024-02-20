package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

/**
 * The FindCommand class represents a command to find task(s) with the specified keyword(s).
 */
public class FindCommand extends Command {
    private final String[] keywords;

    /**
     * Constructs a new FindCommand object with the specified parameters.
     *
     * @param keywords The keyword(s) we are using to find the task(s).
     */
    public FindCommand(String ... keywords) {
        this.keywords = keywords;
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.storeFoundMessage(taskList.getTasksWithKeywords(this.keywords));
    }

}
