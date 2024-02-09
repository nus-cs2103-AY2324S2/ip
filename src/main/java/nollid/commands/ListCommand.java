package nollid.commands;

import nollid.Storage;
import nollid.TaskList;

/**
 * ListCommand class represents a command for listing all tasks in the task list.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class ListCommand extends Command {
    /**
     * Overrides the execute method from the Command class.
     * Executes the command to display the tasks in the to-do list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        // List items in to-do list
        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        if (tasks.isEmpty()) {
            response = new StringBuilder("Your list is empty!");
        }

        for (int i = 0; i < tasks.size(); i++) {
            if (i < tasks.size() - 1) {
                response.append(i + 1)
                        .append(".")
                        .append(tasks.get(i).toString())
                        .append("\n");
            } else {
                response.append(i + 1)
                        .append(".")
                        .append(tasks.get(i).toString());
            }
        }
        return response.toString();
    }
}
