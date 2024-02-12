package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.WrongIndexException;

/**
 * Class for update
 */
public class Update implements Command {
    private final int operand;
    private TaskList tasks;

    /**
     * Constructor
     * @param operand index
     * @param updateField update field
     * @param updateValue update value
     * @param taskList task list
     * @throws DukeException can raise wrong index exception
     */
    public Update(int operand, String updateField, String updateValue, TaskList taskList) throws DukeException {
        if (operand >= taskList.getListLength() || operand < 0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.operand = operand;
        taskList.updateTask(operand, updateField, updateValue);
        this.tasks = taskList;
    }

    /**
     * Reply
     * @return reply
     */
    @Override
    public String reply() {
        return String.format("Nice! I've update this task:\n    %s\n", tasks.getTask(operand));
    }
}
