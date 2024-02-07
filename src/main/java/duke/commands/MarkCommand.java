package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * The `MarkCommand` class is a subclass of the `Command` class that represents a command to mark a task as
 * complete.
 */
public class MarkCommand extends Command {
    private final String details;
    private List<String> messages = new ArrayList<>();

    public MarkCommand(String details) {
        this.details = details;
    }
    
    /**
     * The function takes a task list and a task number as input, marks the corresponding task as complete, and
     * returns a list of messages to be printed out for user later.
     * 
     * @param tasks A TaskList object that contains a list of tasks.
     * @return List of Strings or messages.
     */
    public List<String> execute(TaskList tasks) throws DukeException {
        if(details.length() < 1) { throw new DukeException(
                "Please enter the tasks number that you want to mark as incomplete: ex. mark 2"); }

        try {
            int i = Integer.parseInt(details) - 1;
            tasks.get(i).markAsDone();
            messages.add("OK, I've marked this tasks as not done yet");
            messages.add(tasks.get(i).toString());
        } catch(Exception e) {
            throw new DukeException("Please enter the valid tasks number");
        }

        return messages;
    }
}
