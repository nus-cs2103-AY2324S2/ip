package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a command to add a simple todo.
 */
public class TodoCommand extends AddTaskCommand {
    public TodoCommand(String body) {
        super(body);
    }

    @Override
    protected Task getTaskFromDescription(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.",
                    "Sorry, you need to tell me what you want to add. I can't add empty tasks.");
        }
        return new Todo(description);
    }
}
