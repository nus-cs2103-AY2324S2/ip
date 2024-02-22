package command;

import storage.Storage;
import task.Task;
import task.TaskList;

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
        assert query != null;
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = "Here are the matching tasks!\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(this.query)) {
                response += (i + 1) + ". " + task + "\n";
            }
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
