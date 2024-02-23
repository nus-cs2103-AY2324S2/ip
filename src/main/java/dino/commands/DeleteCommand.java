package dino.commands;

import dino.DinoException.DinoException;
import dino.tasks.TaskList;
import dino.tasks.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * The `DeleteCommand` class is used to delete a task from a task list and returns a list of messages indicating
 * the success of the operation.
 */
public class DeleteCommand extends Command {
    private final String details;
    private final List<String> messages = new ArrayList<>();


    public DeleteCommand(String details) {
        this.details = details;
    }

    /**
     * This Java function deletes a task from a task list and returns a list of messages indicating the success of
     * the operation.
     * 
     * @param tasks A TaskList object that contains a list of tasks.
     * @return List of Strings messages which will be printed to user later.
     */
    public List<String> execute(TaskList tasks) throws DinoException {
        if (details.length() < 1) {
            throw new DinoException("Please enter the tasks number that you want to delete: ex. delete 2");
        }
        try {
            int index = Integer.parseInt(details) - 1;
            Task task = tasks.get(index);
            messages.add("Noted. I've removed this tasks:");
            messages.add(task.toString());
            tasks.remove(index);
            messages.add("Now you have " + tasks.size() + " tasks in the list.");
        } catch(Exception e) {
            throw new DinoException("Please enter the valid tasks number");
        }
        return messages;
    }
}
