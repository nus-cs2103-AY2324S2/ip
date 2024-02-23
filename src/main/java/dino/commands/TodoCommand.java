package dino.commands;

import dino.DinoException.DinoException;
import dino.tasks.TaskList;
import dino.tasks.Todo;
import java.util.ArrayList;
import java.util.List;

/**
 * The `TodoCommand` class is a subclass of the `Command` class that represents a command to add a new todo task
 * to a task list.
 */
public class TodoCommand extends Command {
    private final String details;
    private final List<String> messages = new ArrayList<>();


    public TodoCommand(String details) {
        this.details = details;
    }

    /**
     * The function adds a new task to a task list and returns a list of messages indicating the success of the
     * operation.
     * 
     * @param tasks A TaskList object that represents a list of tasks.
     * @return List of Strings messages which will be printed to user later.
     */
    public List<String> execute(TaskList tasks) throws DinoException {
        if (details.isEmpty()) {
            throw new DinoException("Please enter tasks description");
        }
        tasks.add(new Todo(details));
        messages.add("Got it. I've added this tasks:");
        messages.add("added: " + tasks.get(tasks.size() - 1).toString());
        messages.add("Now you have " + tasks.size() + " tasks in the list.");
        return messages;
    }
}
