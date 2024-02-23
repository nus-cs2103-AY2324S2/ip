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
        if (details.isEmpty()) {
            throw new DinoException("Please enter the task number that you want to delete. Example: 'delete 2'");
        }
        
        try {
            int index = Integer.parseInt(details) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DinoException("The task number you provided is out of range.");
            }

            messages.add("Noted. I've removed this task:");
            messages.add(tasks.get(index).toString());
            messages.add("Now you have " + tasks.size() + " tasks in the list.");
            
            tasks.remove(index);
        } catch (NumberFormatException e) {
            throw new DinoException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DinoException("The task number you provided is out of range.");
        }

        return messages;
    }
}