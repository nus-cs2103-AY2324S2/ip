package duke.command;

import duke.TaskList;
import duke.exception.DukeException;

/**
 * Class for tag priority
 */
public class Priority extends Tag {
    private String priorityLevel;

    /**
     * Constructor
     * @param operand index
     * @param priorityLevel priority
     * @param taskList task list
     * @throws DukeException throw wrong index exception
     */
    public Priority(int operand, String priorityLevel, TaskList taskList) throws DukeException {
        super(operand, priorityLevel, taskList);
        this.priorityLevel = priorityLevel;
    }
}
