package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.storage.Storage;
import jade.ui.Ui;

/**
 * The <code>AddCommand</code> object represents the command to add a task.
 */
public class AddCommand extends Command {
    private final Task task; // the task to be added.

    /**
     * Class constructor specifying the task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @inheritDoc This implementation prints an add message after the task is added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        String result = String.format("Got it. I've added this task:\n\t %s\n"
                + "Now you have %d task(s) in the list.", task, tasks.size());
        ui.printMessage(result);
        return result;
    }

    /**
     * @inheritDoc The AddCommand does not indicate the exit of the program.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
