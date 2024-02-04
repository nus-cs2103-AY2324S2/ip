package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.TagNotExistException;
import duke.exception.WrongIndexException;

public class Untag implements Command {
    private int operand;
    private TaskList tasks;

    public Untag(int operand, String tagName, TaskList taskList) throws DukeException {
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

    @Override
    public String reply() {
        return String.format("Nice! I've tag this task:\n%s\n", tasks.getTask(operand));
    }
}
