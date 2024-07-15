package hanxiao.command;

import hanxiao.TaskList;
import hanxiao.exception.HanxiaoException;
import hanxiao.exception.WrongIndexException;

/**
 * Class for update
 */
public class Updating implements Command {
    private final int operand;
    private TaskList tasks;

    /**
     * Constructor
     *
     * @param operand index.
     * @param updateField update field.
     * @param updateValue update value.
     * @param taskList task list.
     * @throws HanxiaoException can raise wrong index exception.
     */
    public Updating(int operand, String updateField, String updateValue, TaskList taskList) throws HanxiaoException {
        if (operand >= taskList.getListLength() || operand < 0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.operand = operand;
        taskList.updateTask(operand, updateField, updateValue);
        this.tasks = taskList;
    }

    /**
     * Reply
     *
     * @return reply.
     */
    @Override
    public String reply() {
        return String.format("Nice! I've update this task:\n    %s\n", tasks.getTask(operand));
    }
}
