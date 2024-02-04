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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printMessage(String.format("\tGot it. I've added this task:\n\t %s\n\t"
                + "Now you have %d task(s) in the list.", task, tasks.size()));
    }

    /**
     * @inheritDoc The AddCommand does not indicate the exit of the program.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
