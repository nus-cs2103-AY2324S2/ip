package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.Locale;

public class FindCommand extends Command {
    String searchTerm;

    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm.toLowerCase();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList searchResults = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.match(searchTerm)) {
                searchResults.add(currTask);
            }
        }

        if (searchResults.size() != 0) {
            System.out.println(searchResults);
        } else {
            System.out.println("Sorry, there are no matching entries.");
        }
    }
}
