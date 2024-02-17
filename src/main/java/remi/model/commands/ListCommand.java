package remi.model.commands;

import remi.io.Message;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * Represents the list command and its functionality.
 */
public class ListCommand implements Command {

    /**
     * This returns a message containing the string representation of the taskList.
     *
     * @param args the arguments of the message (this excludes the first word of the command)
     * @param taskList the taskList object
     * @param chatbot the chatbot object
     * @return Message representation of taskList
     * @throws RemiError not thrown
     */
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        return new Message(taskList.toString());
    }
}
