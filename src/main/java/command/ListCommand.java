package command;

import dook.Storage;
import task.TaskList;

public class ListCommand extends Command {

    /**
     * Lists the current tasks
     *
     * @param tasks The bot TaskList.
     * @param storage The storage interface.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.size() == 0) {
            return "You have no tasks!!";
        }
        String toReturn = "Here are your tasks!\n";
        toReturn += tasks.toString();
        return toReturn;
    }
}
