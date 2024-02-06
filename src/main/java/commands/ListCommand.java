package commands;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;

public class ListCommand extends Command {
    private final TaskList tasks;
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo("Here are the tasks in your list: ");
        int i = 1;
        for (Task eachTask : this.tasks.getTasks()) {
            ui.echo(i++ + ". " + eachTask);
        }
    }
}
