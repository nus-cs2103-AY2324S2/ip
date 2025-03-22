package felix.command;

import felix.exception.FelixException;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Class representing command to find matching tasks
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns String representation of tasks containing keyword.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        return ui.getFindMessage(tasks.filterByKeyword(this.keyword).toString());
    }
}
