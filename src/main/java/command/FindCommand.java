package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents Command that finds task based on user input.
 */
public class FindCommand extends Command {
    protected String query;

    /**
     * Creates FindCommand with specified task to find.
     *
     * @param query Query phrase to search for in task list.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFind();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(this.query)) {
                System.out.println((i + 1) + ". " + task);
            }
        }
    }
}
