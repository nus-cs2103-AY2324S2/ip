package action;

import print.Printer;
import task.TaskList;

/**
 * InvalidAction encapsulates the behaviour of an invalid action.
 *
 * @author Titus Chew
 */
public class InvalidAction extends Action {
    /**
     * Constructor for this invalid action.
     */
    public InvalidAction() {
        super(Command.INVALID);
    }

    /**
     * Handles the case when the command is invalid.
     *
     * @param taskList the taskList that is used with the chatbot
     */
    @Override
    public void execute(TaskList taskList) {
        // Perform behaviour
        Printer.printMessages("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
