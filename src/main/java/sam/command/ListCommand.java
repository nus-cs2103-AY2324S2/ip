package sam.command;

import java.util.ArrayList;

import sam.Storage;
import sam.TaskList;
import sam.task.Task;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        StringBuilder notice = new StringBuilder("Here is the list of tasks:\n");
        for (int i = 0; i < list.size(); i++) {
            notice.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return notice + "\n";
    }
}