package chaterpillar.commands;

import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

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
