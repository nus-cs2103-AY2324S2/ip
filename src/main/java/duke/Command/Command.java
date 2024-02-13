package duke.Command;
import duke.TaskList;
import duke.DukeException;
public abstract class Command {
    public abstract String execute(TaskList taskList, String command) throws DukeException;
}