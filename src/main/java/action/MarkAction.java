package action;

import print.Printer;
import task.TaskList;

/**
 * MarkAction encapsulates the behaviour of marking a task as done.
 *
 * @author Titus Chew
 */
public class MarkAction extends Action {
    /**
     * Constructor for this mark action.
     *
     * @param arguments the arguments supplied with the command
     */
    public MarkAction(Argument[] arguments) {
        super(Command.MARK, arguments);
    }

    /**
     * Marks and prints the task.
     *
     * @param taskList the taskList that is used with the chatbot
     */
    @Override
    public void execute(TaskList taskList) {
        // Validate arguments
        if (findDefaultArgument() == null) {
            handleMissingArgument(getCommand(), "index");
            return;
        }

        // Perform behaviour
        int index = Integer.parseInt(findDefaultArgument()) - 1;
        taskList.markTask(index);
        Printer.printMessages(
                "Nice! I've marked this task as done:",
                "    " + taskList.getTask(index)
        );
    }
}
