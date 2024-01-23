package action;

import print.Printer;
import task.TaskList;

/**
 * ByeAction encapsulates the behaviour when ending the chat.
 *
 * @author Titus Chew
 */
public class ByeAction extends Action {
    public ByeAction() {
        super(Command.BYE);
    }

    /**
     * Greets the user when exiting the application.
     */
    @Override
    public void execute(TaskList taskList) {
        // Perform behaviour
        Printer.printMessages("Bye! Hope to see you again soon!");
    }
}
