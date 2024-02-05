package commands;
import DukeException.DukeException;
import tasks.TaskList;

public interface Command {
    void execute(String details, TaskList tasks) throws DukeException;
}
