package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>DeleteCommand</code> object represents the command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index; // the index of the task to be deleted

    /**
     * Class constructor specifying the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * @inheritDoc This implementation prints a delete message after the task is deleted.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws JadeException {
        if (index <= 0 || index > taskList.size()) {
            throw new JadeException("Please input a valid number to delete the task.");
        }
        Task deletedTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        String result = String.format("OK, I've deleted this task:\n\t  %s\n"
                + "Now you have %d task(s) in the list.", deletedTask, taskList.size());
        try {
            storage.saveChange(taskList);
        } catch (JadeException e) {
            return e.getMessage();
        }
        return result;
    }

    /**
     * @inheritDoc The DeleteCommand does not indicate the exit of the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
