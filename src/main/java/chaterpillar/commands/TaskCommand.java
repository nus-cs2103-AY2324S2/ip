package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to add a task to the list of tasks.
 *
 * @author marclamp
 */
public class TaskCommand extends Command {
    private final Task task;

    /**
     * Constructor for this class.
     *
     * @param task the <code>Task</code> to be added to the list.
     */
    public TaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list of tasks.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @return reply from the ChatBot.
     * @throws ChaterpillarException if there are errors writing to the file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        tasks.addTask(this.task);
        storage.saveAllToFile(tasks);

        String output =
                "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list.";
        ui.echo(output);
        return output;
    }
}
