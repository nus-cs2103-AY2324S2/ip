package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;
import duke.tasks.Task;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends Command {
    private final String details;
    private final List<String> messages = new ArrayList<>();


    public DeleteCommand(String details) {
        this.details = details;
    }
    public List<String> execute(TaskList tasks) throws DukeException {
        if (details.length() < 1) {
            throw new DukeException("Please enter the tasks number that you want to delete: ex. delete 2");
        }
        try {
            int index = Integer.parseInt(details) - 1;
            Task task = tasks.get(index);
            messages.add("Noted. I've removed this tasks:");
            messages.add(task.toString());
            tasks.remove(index);
            messages.add("Now you have " + tasks.size() + " tasks in the list.");
        } catch(Exception e) {
            throw new DukeException("Please enter the valid tasks number");
        }
        return messages;
    }
}
