package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

public class UnmarkCommand extends Command {
    private final String details;
    private List<String> messages = new ArrayList<>();

    public UnmarkCommand(String details) {
        this.details = details;
    }

    public List<String> execute(TaskList tasks) throws DukeException {
        if (details.length() < 1) {
            throw new DukeException(
                    "Please enter the tasks number that you want to mark as incomplete: ex. mark 2");
        }
        try {
            int i = Integer.parseInt(details) - 1;
            tasks.get(i).markAsUndone();
            messages.add("OK, I've marked this tasks as not done yet");
            messages.add(tasks.get(i).toString());
        } catch (Exception e) {
            throw new DukeException("Please enter the valid tasks number");
        }
        return messages;
    }
}
