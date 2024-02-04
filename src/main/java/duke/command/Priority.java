package duke.command;

import duke.TaskList;
import duke.exception.DukeException;

public class Priority extends Tag {
    String priorityLevel;
    public Priority(int operand, String priorityLevel, TaskList taskList) throws DukeException {
        super(operand, priorityLevel, taskList);
        this.priorityLevel = priorityLevel;
    }
}
