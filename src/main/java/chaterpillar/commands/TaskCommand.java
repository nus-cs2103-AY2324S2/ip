package chaterpillar.commands;

import java.io.IOException;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

/**
 * <code>Command</code> to add a task to the list of tasks.
 */
public class TaskCommand extends Command {
    private final Task task;

    /**
     * Constructor for this class.
     * @param task the <code>Task</code> to be added to the list.
     */
    public TaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list of tasks.
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @throws ChaterpillarException if there are errors writing to the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        tasks.addTask(this.task);

        ui.echo("Got it. I've added this task:");
        ui.echo(task.toString());
        ui.echo("Now you have " + tasks.size() + " tasks in the list.");

        storage.saveAllToFile(tasks);
    }
}
