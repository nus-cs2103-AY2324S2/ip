package felix.command;

import felix.exception.FelixException;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.println("Here are the matching tasks in your list:");
        ui.println(tasks.filterByKeyword(this.keyword));
    }
}
