package commands;
import DukeException.DukeException;
import tasks.TaskList;

public abstract class Command {
    public void execute(TaskList tasks) throws DukeException {}
}
