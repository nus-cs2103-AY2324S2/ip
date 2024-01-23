package action;

import task.TaskList;

/**
 * ListAction encapsulates the behaviour when listing the tasks.
 *
 * @author Titus Chew
 */
public class ListAction extends Action{
    /**
     * Constructor for this list action.
     */
    public ListAction() {
        super(Command.LIST);
    }

    /**
     * Prints the user's list.
     *
     * @param taskList the taskList that is used with the chatbot
     */
    @Override
    public void execute(TaskList taskList) {
        print.Printer.printMessages(
                "Here are the tasks in your list:",
                taskList.toString()
        );
    }
}
