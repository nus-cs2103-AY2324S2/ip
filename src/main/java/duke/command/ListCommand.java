package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Class representing List commands.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }
}
