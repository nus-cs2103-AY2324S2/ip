package action;

import print.Printer;
import task.TaskList;

/**
 * InvalidAction encapsulates the behaviour of an invalid action.
 *
 * @author Titus Chew
 */
public class InvalidAction extends Action {
    public InvalidAction() {
        super(Command.INVALID);
    }

    /**
     * Handles the case when the command is invalid.
     * @param taskList The taskList that is used with the ChatBot.
     */
    @Override
    public void execute(TaskList taskList) {
        // Perform behaviour
        Printer.printMessages("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
