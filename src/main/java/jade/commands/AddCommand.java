package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

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
    public String execute(TaskList taskList, Storage storage) {
        taskList.add(task);
        String result = String.format("Got it. I've added this task:\n\t %s\n"
                + "Now you have %d task(s) in the list.", task, taskList.size());
        try {
            storage.saveChange(taskList);
        } catch (JadeException e) {
            return e.getMessage();
        }
        return result;
    }

    /**
     * @inheritDoc The AddCommand does not indicate the exit of the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
