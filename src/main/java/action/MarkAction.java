package action;

import print.Printer;
import task.TaskList;

/**
 * MarkAction encapsulates the behaviour of marking a task as done.
 *
 * @author Titus Chew
 */
public class MarkAction extends Action {
    public MarkAction(Argument[] arguments) {
        super(Command.MARK, arguments);
    }

    /**
     * Marks and prints the task.
     * @param taskList The taskList that is used with the ChatBot.
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
