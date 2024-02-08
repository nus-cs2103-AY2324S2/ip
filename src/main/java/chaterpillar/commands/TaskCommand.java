package chaterpillar.commands;

import java.io.IOException;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;
public class TaskCommand extends Command {
    private final Task task;

    public TaskCommand(Task task) {
        this.task = task;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException, IOException {
        tasks.addTask(this.task);
        ui.echo("Got it. I've added this task:");
        ui.echo(task.toString());
        ui.echo("Now you have " + tasks.size() + " tasks in the list.");
        storage.saveAllToFile(tasks);
    }
}
