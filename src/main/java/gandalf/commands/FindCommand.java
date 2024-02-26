package gandalf.commands;

import gandalf.Storage;
import gandalf.TaskList;
import gandalf.Ui;

/**
 * Executes the TaskList's filter method to find all tasks that matches given keyword.
 */
public class FindCommand extends Command {

    String keyword;
    public FindCommand(String commandName, TaskList tasks, Storage storage, Ui ui, String keyword) {
        super(commandName, tasks, storage, ui);
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        TaskList filteredList = tasks.filter(this.keyword);
        return filteredList.list();
    }
}
