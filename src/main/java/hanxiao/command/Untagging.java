package hanxiao.command;

import hanxiao.TaskList;
import hanxiao.exception.HanxiaoException;
import hanxiao.exception.TagNotExistException;
import hanxiao.exception.WrongIndexException;

/**
 * Class for untag
 */
public class Untagging implements Command {
    private int operand;
    private TaskList tasks;

    /**
     * Constructor
     *
     * @param operand index.
     * @param tagName tag name.
     * @param taskList task list.
     * @throws HanxiaoException throw wrong index exception.
     */
    public Untagging(int operand, String tagName, TaskList taskList) throws HanxiaoException {
        if (operand >= taskList.getListLength() || operand < 0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.operand = operand;
        boolean status = taskList.getTask(this.operand).removeTag(tagName);
        if (!status) {
            throw new TagNotExistException(tagName);
        }
        this.tasks = taskList;
    }

    /**
     * Reply
     *
     * @return reply.
     */
    @Override
    public String reply() {
        return String.format("Nice! I've remove the tag:\n%s\n", tasks.getTask(operand));
    }
}
