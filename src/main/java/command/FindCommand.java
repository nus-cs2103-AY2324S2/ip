package command;

import java.util.ArrayList;

import dook.DookException;
import dook.Storage;
import task.Task;
import task.TaskList;

public class FindCommand extends Command {

    private final String target;

    public FindCommand(String target) {
        this.target = target;
    }

    /**
     * Finds matching tasks from the TaskList, using a given search word.
     *
     * @param tasks The bot TaskList.
     * @param storage The storage interface.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> found = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasSubstring(target)) {
                found.add(tasks.get(i));
            }
        }
        if (found.isEmpty()) {
            return "No tasks foundd :(( meoww";
        }
        String toReturn = "";
        toReturn += "Okay! here are the tasks I found! :3\n";
        for (int i = 0; i < found.size(); i++) {
            toReturn += String.format("%d. %s \n", i + 1, found.get(i).toString());
        }
        return toReturn;
    }
}
