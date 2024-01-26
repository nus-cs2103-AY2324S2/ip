package duke.command;

import duke.TaskList;
import duke.command.Command;
import duke.exception.WrongIndexException;

/**
 * class for mark an task finish.
 */
public class Mark implements Command {
    private final int oprand;
    private TaskList tasks;
    /**
     * Constructor
     * change the status of task to finished.
     * @param operand which task to mark from 0
     * @throws WrongIndexException invalid index
     */
    public Mark(int operand, TaskList taskList) throws WrongIndexException{
        if (operand>=taskList.getListLength() || operand<0) {
            throw new WrongIndexException(taskList.getListLength());
        }
        this.oprand=operand;
        taskList.getTask(oprand).Done();
        this.tasks = taskList;
    }

    /**
     * Override the reply method in interface
     */
    @Override
    public String reply() {
        return String.format("    Nice! I've marked this task as done:\n    %s\n",tasks.getTask(oprand));
    }
}
