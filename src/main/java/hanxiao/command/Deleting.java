package hanxiao.command;

import hanxiao.TaskList;
import hanxiao.exception.WrongIndexException;
import hanxiao.task.Task;

/**
 * Class for the delete command.
 */
public class Deleting implements Command {
    private final int operand;
    private final Task deletedTask;
    private TaskList tasks;
    /**
     * Constructor
     *
     * @param operand the index count from 0.
     */
    public Deleting(int operand, TaskList taskList) throws WrongIndexException {
        this.operand = operand;
        if (operand >= taskList.getListLength() || operand < 0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.deletedTask = taskList.removeTask(operand);
        this.tasks = taskList;
    }

    /**
     * Print the deleted task and the number of tasks remaining
     */
    @Override
    public String reply() {
        assert this.deletedTask != null : "The deletion is not performed";
        return String.format("Noted. I've removed this task:\n"
                + "  %s\nNow you have %s tasks in the list.\n",
                this.deletedTask, this.tasks.getListLength());
    }
}
