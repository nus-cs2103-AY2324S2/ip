package action;

import print.Printer;
import task.TaskList;

/**
 * UnmarkAction encapsulates the behaviour of marking a task as not done.
 *
 * @author Titus Chew
 */
public class UnmarkAction extends Action {
    /**
     * Constructor for this unmark action.
     *
     * @param arguments the arguments supplied with the command
     */
    public UnmarkAction(Argument[] arguments) {
        super(Command.UNMARK, arguments);
    }

    /**
     * Unmarks and prints the task.
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
        taskList.unmarkTask(index);
        Printer.printMessages(
                "Ok, I've marked this task as not done yet:",
                "    " + taskList.getTask(index)
        );
    }
}
