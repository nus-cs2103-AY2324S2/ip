package hanxiao.command;

import hanxiao.TaskList;
import hanxiao.exception.HanxiaoException;

/**
 * Class for tag priority
 */
public class Priority extends Tagging {
    private String priorityLevel;

    /**
     * Constructor
     *
     * @param operand index.
     * @param priorityLevel priority.
     * @param taskList task list.
     * @throws HanxiaoException throw wrong index exception.
     */
    public Priority(int operand, String priorityLevel, TaskList taskList) throws HanxiaoException {
        super(operand, priorityLevel, taskList);
        this.priorityLevel = priorityLevel;
    }
}
