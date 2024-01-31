package commands;

import exceptions.DukeException;
import tasklists.TaskList;

import java.io.IOException;

public class Command {
    protected TaskList tasks;

    public void execute() throws DukeException, IOException {
        throw new DukeException("Invalid call of execute()");
    };

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
