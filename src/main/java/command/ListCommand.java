package command;

import storage.Storage;
import task.TaskList;

/**
 * Represents Command that lists task in task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = "Here you go buddy!:\n";
        for (int i = 0; i < tasks.size(); i++) {
            response += (i + 1) + ". " + tasks.getTask(i) + "\n";
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
