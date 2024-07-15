package hanxiao.command;

import hanxiao.TaskList;
import hanxiao.exception.WrongIndexException;

/**
 * Class for mark an task to undone.
 */
public class Unmarking implements Command {
    private final int operand;
    private TaskList tasks;

    /**
     * Constructor change the status of task to unDone.
     *
     * @param operand which task to mark from 0.
     * @throws WrongIndexException index invalid.
     */
    public Unmarking(int operand, TaskList taskList) throws WrongIndexException {
        if (operand >= taskList.getListLength() || operand < 0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.operand = operand;
        taskList.getTask(this.operand).unDone();
        this.tasks = taskList;
    }

    /**
     * Override the reply method in interface
     */
    @Override
    public String reply() {
        return String.format("OK, I've marked this task as not done yet:\n%s\n", tasks.getTask(operand));
    }
}
