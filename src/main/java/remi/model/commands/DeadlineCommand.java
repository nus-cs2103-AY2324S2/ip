package remi.model.commands;

import remi.io.Message;
import remi.model.Deadline;
import remi.model.TaskList;
import remi.model.Ui;
import remi.parsing.Parser;
import remi.utils.RemiError;

/**
 * Command representing the creation of an deadline.
 */
public class DeadlineCommand implements Command {

    /**
     * This function creates a new Deadline in the taskList.
     * It also returns a message confirming the deadline is added and gives a summary of the new taskList.
     *
     * @param args     the arguments for creating a Deadline
     * @param taskList the taskList object
     * @param chatbot the chatbot object
     * @return the message confirming creation of the deadline
     * @throws RemiError thrown if any options or label is missing
     */
    @Override
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        String label = Parser.getLabel(args);
        String by = Parser.findOption("/by", args);
        Deadline deadline = new Deadline(label, by);
        taskList.addTask(deadline);
        return new Message(String.format(
                "I've added the task.\n%s\nYou still have %d tasks in the list.",
                deadline, taskList.size())
        );

    }
}
