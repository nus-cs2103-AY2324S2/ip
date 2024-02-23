package henry.command;

import java.util.ArrayList;

import henry.Storage;
import henry.TaskList;
import henry.task.Task;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        StringBuilder ret = new StringBuilder("Here is a list of tasks:\n");
        for (int i = 0; i < list.size(); i = i + 1) {
            ret.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return ret + "\n";
    }
}
