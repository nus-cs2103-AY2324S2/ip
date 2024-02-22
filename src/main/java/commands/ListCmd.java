package commands;

import destiny.DestinyException;
import destiny.TaskList;

/**
 * Command that lists all elements in the tasklist.
 */
public class ListCmd extends Command {

    /**
     * Collates each task in the TaskList 'tasks' into a string.
     *
     * @return String of all elements in tasklist.
     * @throws DestinyException If no elements in the list.
     */
    @Override
    public String execute(TaskList tasks) throws DestinyException {
        if (tasks.size() == 0) {
            throw new DestinyException("There's nothing in your list so far");
        }
        String listStr = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            listStr += "\n " + j + ". " + tasks.get(i).toString();
        }
        return listStr;
    }
}
