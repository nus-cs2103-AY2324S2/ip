package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {
    private List<String> messages = new ArrayList<>();
    public ListCommand() {};

    public List<String> execute(TaskList tasks) throws DukeException {
        messages.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            messages.add("  " + (i + 1) + "." + tasks.get(i).toString());
        }
        return messages;
    }
}
