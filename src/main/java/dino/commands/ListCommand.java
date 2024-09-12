package dino.commands;

import dino.DinoException.DinoException;
import dino.tasks.TaskList;
import java.util.ArrayList;
import java.util.List;

/**
 * The `ListCommand` class is a subclass of the `Command` class that executes a task list and returns a list of
 * messages containing the tasks in the list.
 */
public class ListCommand extends Command {

    private List<String> messages = new ArrayList<>();
    

    public ListCommand() {};

    /**
     * The function executes a task list and returns a list of messages containing the tasks in the list.
     * 
     * @param tasks A TaskList object that contains a list of tasks.
     * @return List of Strings: list of all the tasks in the task list which will be printed to user later.
     */
    public List<String> execute(TaskList tasks) throws DinoException {
        messages.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            messages.add("  " + (i + 1) + "." + tasks.get(i).toString());
        }

        return messages;
    }
}
