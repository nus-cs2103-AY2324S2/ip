package dino.commands;

import dino.DinoException.DinoException;
import dino.tasks.TaskList;
import java.util.ArrayList;
import java.util.List;

/**
 * The `UnmarkCommand` class is a subclass of the `Command` class that represents a command to mark a task as
 * incomplete.
 */
public class UnmarkCommand extends Command {
    private final String details;
    private final List<String> messages = new ArrayList<>();


    public UnmarkCommand(String details) {
        this.details = details;
    }

    /**
     * The function takes a task list and a task number as input, unmarks the corresponding task as incomplete, and
     * returns a list of messages to be printed out for user later.
     * 
     * @param tasks A TaskList object that contains a list of tasks.
     * @return List of Strings or messages.
     */
    public List<String> execute(TaskList tasks) throws DinoException {
        if (details.isEmpty()) {
            throw new DinoException("Please enter the task number that you want to mark as incomplete. Example: 'unmark 2'");
        }
        
        try {
            int i = Integer.parseInt(details) - 1;
            tasks.get(i).markAsUndone();
            messages.add("OK, I've marked this tasks as not done yet");
            messages.add(tasks.get(i).toString());
        } catch (NumberFormatException e) {
            throw new DinoException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DinoException("The task number you provided is out of range.");
        }

        return messages;
    }
}
