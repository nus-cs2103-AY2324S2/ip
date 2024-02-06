package commands;

import java.io.IOException;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.echo("Ok, I've marked this task as not done yet:");
        Task currTask = tasks.get(index-1);
        currTask.unmark();
        ui.echo(currTask.toString());
        storage.saveAllToFile(tasks);
    }
}
