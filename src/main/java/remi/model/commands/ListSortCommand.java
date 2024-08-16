package remi.model.commands;

import remi.io.Message;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * Represents the list command and its functionality.
 */
public class ListSortCommand implements Command {
    /**
     * This returns a message containing the taskList sorted in alphabetical order.
     * This does not change the state of the taskList.
     *
     * @param args the arguments of the message (this excludes the first word of the command)
     * @param taskList the taskList object
     * @param chatbot the chatbot object
     * @return the sorted version of the taskList.
     * @throws RemiError not thrown
     */
    @Override
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        return new Message("Your alphabetical task list:\n" + taskList.getSortedTasks().toString());
    }
}
