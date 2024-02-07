package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;
import java.util.List;

public abstract class Command {
    public abstract List<String> execute(TaskList tasks) throws DukeException;
}
