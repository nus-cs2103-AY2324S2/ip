package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.WrongIndexException;

public class Update implements Command{

    private final int operand;
    private TaskList tasks;

    public Update(int operand, String updateField, String updateValue, TaskList taskList) throws DukeException {
        if (operand >= taskList.getListLength() || operand < 0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.operand = operand;
        taskList.updateTask(operand,updateField,updateValue);
        this.tasks = taskList;
    }

    @Override
    public String reply() {
        return String.format("Nice! I've update this task:\n    %s\n", tasks.getTask(operand));
    }
}
