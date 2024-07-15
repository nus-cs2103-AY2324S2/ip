package hanxiao.command;

import hanxiao.TaskList;
import hanxiao.exception.WrongIndexException;

/**
 * class for mark an task finish.
 */
public class Marking implements Command {
    private final int operand;
    private TaskList tasks;

    /**
     * Constructor
     * change the status of task to finished.
     *
     * @param operand which task to mark from 0.
     * @throws WrongIndexException invalid index.
     */
    public Marking(int operand, TaskList taskList) throws WrongIndexException {
        if (operand >= taskList.getListLength() || operand < 0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.operand = operand;
        taskList.getTask(this.operand).setDone();
        this.tasks = taskList;
    }

    /**
     * Override the reply method in interface
     */
    @Override
    public String reply() {
        return String.format("Nice! I've marked this task as done:\n%s\n", tasks.getTask(operand));
    }
}
