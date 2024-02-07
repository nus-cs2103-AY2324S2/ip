package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoCommand extends Command {
    private final String details;
    private List<String> messages = new ArrayList<>();

    public TodoCommand(String details) {
        this.details = details;
    }

    public List<String> execute(TaskList tasks) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException("Please enter tasks description");
        }
        tasks.add(new Todo(details));
        messages.add("Got it. I've added this tasks:");
        messages.add("added: " + tasks.get(tasks.size() - 1).toString());
        messages.add("Now you have " + tasks.size() + " tasks in the list.");
        return messages;
    }

}
