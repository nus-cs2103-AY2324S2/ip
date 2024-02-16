package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>AddCommand</code> object represents the command to add a task.
 */
public class AddCommand extends Command {
    private static final String RESULT_MSG_FORMATTED = "Got it. I've added this task:\n\t %s\n"
            + "Now you have %d task(s) in the list.";
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
    public String execute(TaskList taskList, Storage storage) throws JadeException {
        taskList.add(task);
        String result = String.format(RESULT_MSG_FORMATTED, task, taskList.size());
        storage.saveChange(taskList);
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
