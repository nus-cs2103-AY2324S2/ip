package remi.model.commands;
import remi.io.Message;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * Interface for creating Commands, must override the run method.
 */
@FunctionalInterface
public interface Command {
    /**
     * The function in this lambda method. This is meant to be implemented.
     *
     * @param args the arguments of the message (this excludes the first word of the command)
     * @param taskList the taskList object
     * @param chatbot the chatbot object
     * @return the message to be outputted
     * @throws RemiError thrown if any known runtime errors occur
     */
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError;
}
