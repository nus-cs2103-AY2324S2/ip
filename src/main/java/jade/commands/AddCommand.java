package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.ui.Ui;
import jade.storage.Storage;
import jade.exception.JadeException;

/**
 * The <code>AddCommand</code> object represents the command to add a task.
 */
public class AddCommand extends Command {
    private Task task; // the task to be added.

    /**
     * Class constructor specifying the task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @inheriDocs This implementation prints an add message after the task is added.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printMessage(String.format("\tGot it. I've added this task:\n\t %s\n\tNow you have %d task(s) in the list.", task, tasks.size()));
    }

    /**
     * @inheriDocs The AddCommand does not indicate the exit of the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
