package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;


public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        if (keyword.isBlank()) {
            System.err.println("OOPS! You forget to provide the keyword to search.");
            return false;
        }
        TaskList results = tasks.find(keyword);
        ui.showLine();
        if (results.size() != 0) {
            System.out.println("Here are the matching tasks in your list:");
            results.displayTasks();
        } else {
            System.out.println("There is no matching tasks in your list.");
        }
        ui.showLine();
        return true;
    }
}
